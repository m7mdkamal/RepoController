package com.m7mdkamal.algorithm;

import com.m7mdkamal.buildtool.BuildTool;
import com.m7mdkamal.buildtool.Maven;
import com.m7mdkamal.model.JavaFile;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mohamed on 6/11/16.
 */
public class Algorithm {

    private String username;
    private String algoname;

    private BuildTool btool;

    private String baseDir = "/var/algoritmicloud/";
    private String userDir;
    private String algoDir;

    public Algorithm(String username, String algoname) {
        this.username = username;
        this.algoname = algoname;
        this.btool = new Maven(username, algoname);
        this.userDir = this.baseDir + username + "/";
        this.algoDir = this.userDir + algoname + "/";

//        File tmpFile = new File("/tmp/log.log");
//        try {
//            tmpFile.createNewFile();
//            System.setOut(new PrintStream(new FileOutputStream(tmpFile, true)));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public Result init() {
        //create new directory for the user if not exist
        File parent = new File(userDir);
        parent.mkdir();
        if(repoExist())
            return new Result(Status.FAILURE, "" , new DirectoryNotEmptyException(algoDir));
        String log = "";
        try {
            List<String> lines = new ArrayList<>();
            lines.add(getBaseDirCommand());
            lines.add(this.btool.generate());

            File file = createTempFile(lines);
            System.out.println(FileUtils.readFileToString(file));

            log = this.execute(file);
            return new Result(Status.SUCCESS, log);

        } catch (IOException e) {
            return new Result(Status.FAILURE, log, e);
        }

    }

    public Result compile() {
        return compile(true);
    }

    public Result compile(boolean mvn) {

        if(!repoExist())
            return new Result(Status.FAILURE, "" , new Exception("Repo not found " + algoDir));

        List<String> lines = new ArrayList<>();
        lines.add("cd " + this.algoDir);


        // TODO: 6/16/16 Redo this method again! - Exit Values
        if (mvn)
            lines.add(btool.compile());
        else {
            File file = new File(algoDir + "target/classes/");
            file.mkdirs();
            lines.add("javac src/main/java/" + username + "/*.java -d target/classes/ ");
        }

        File file = null;
        String log = "";
        try {
            file = createTempFile(lines);
            System.out.println(FileUtils.readFileToString(file));
            log = execute(file , new int[]{0});
            return new Result(Status.SUCCESS, log);
        } catch (IOException e) {
            return new Result(Status.FAILURE, log, e);
        }
    }


    // ~/testmvn/client/mohammed/facedetection/target/classes 
    // java  -classpath . mohammed.App mohammed/App.class

    public Result run(String input) {

        List<String> lines = new ArrayList<>();

        lines.add("cd " + this.algoDir + "target/classes");
        lines.add("java  -classpath . " + username + ".App " + input);

        File file = null;

        String out = "";
        try {

            file = createTempFile(lines);
            System.out.println(FileUtils.readFileToString(file));
            out = execute(file);
            return new Result(Status.SUCCESS, out);
        } catch (IOException e) {
            return new Result(Status.SUCCESS, out, e);
        }


    }

    public Result updateFile(String name, String content) {
        File file = new File(this.algoDir + "src/main/java/" + this.username + "/" + name);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileUtils.writeStringToFile(file, content);
            return new Result(Status.SUCCESS, null);
        } catch (IOException e) {
            return new Result(Status.FAILURE, exceptionToString(e));
        }
    }


    public Result updateDependency(String pom) {
        File file = new File(this.algoDir + "pom.xml");
        String log = "";
        try {
            FileUtils.writeStringToFile(file, pom);

            List<String> lines = new ArrayList<>();

            lines.add("cd " + this.algoDir);
            lines.add(btool.updateDependency());

            file = createTempFile(lines);
            System.out.println(FileUtils.readFileToString(file));
            log = execute(file);
            return new Result(Status.SUCCESS, log);

        } catch (IOException e) {
            return new Result(Status.FAILURE, log, e);
        }
    }


    private String getBaseDirCommand() {
        return "cd " + this.baseDir + username + ";\n";
    }

    private String execute(File file) throws IOException {
        return execute(file , new int[]{0, 1});
    }

    private String execute(File file , int[] exitValues) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommandLine cmdLine = new CommandLine(file);
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);

        DefaultExecutor exec = new DefaultExecutor();
        exec.setStreamHandler(streamHandler);
        exec.setExitValues(exitValues);
        exec.execute(cmdLine);

        return outputStream.toString();
    }

    private File createTempFile(List<String> lines) throws IOException {
        // TODO: 6/11/16 delete it.
        lines.add(0, "export JAVA_HOME=/usr/lib/jvm/java-openjdk");
        File tempFile = File.createTempFile("algoscript", ".sh" , new File("/tmp"));
        tempFile.setExecutable(true);
        for (String line : lines) {
            FileUtils.writeStringToFile(tempFile, line + "\n", true);
        }
        return tempFile;
    }

    private String exceptionToString(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public Boolean delete() {
        List<String> lines = new ArrayList<>();
        lines.add("rm -rf " + this.getAlgoDir());

        File file = null;
        try {
            file = createTempFile(lines);
            System.out.println(FileUtils.readFileToString(file));
            this.execute(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean repoExist(){
        File file = new File(algoDir);
        return file.isDirectory();
    }

    public String getUsername() {
        return username;
    }

    public String getAlgoname() {
        return algoname;
    }

    public String getBaseDir() {
        return baseDir;
    }

    public String getUserDir() {
        return userDir;
    }

    public String getAlgoDir() {
        return algoDir;
    }

    public Result getFiles(){
        List<String> lines = new ArrayList<>();

        lines.add("cd " + this.algoDir + "src/main/java/" + username);
        lines.add("ls");
        File file = null;
        try {
            file = createTempFile(lines);
            String output = execute(file);
            return new Result(Status.SUCCESS,output);
        } catch (IOException e) {
            return new Result(Status.FAILURE,"",e);
        }
    }

    public Result getFile(String filename) {
        List<String> lines = new ArrayList<>();

        lines.add("cd " + this.algoDir + "src/main/java/" + username);
        lines.add("cat "+filename);
        File file = null;
        try {
            file = createTempFile(lines);
            String javaClass = execute(file);
            return new Result(Status.SUCCESS,javaClass);
        } catch (IOException e) {
            return new Result(Status.FAILURE,"",e);
        }

    }

    public Result deleteFile(String filename){
        List<String> lines = new ArrayList<>();

        lines.add("cd " + this.algoDir + "src/main/java/" + username);
        lines.add("rm "+filename);
        File file = null;

        try {
            file = createTempFile(lines);

            return new Result(Status.SUCCESS, execute(file));
        } catch (IOException e) {
            return new Result(Status.FAILURE, null , e);

        }
    }
}
