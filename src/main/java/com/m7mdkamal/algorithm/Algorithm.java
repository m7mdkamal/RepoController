package com.m7mdkamal.algorithm;

import com.m7mdkamal.buildtool.BuildTool;
import com.m7mdkamal.buildtool.Maven;
import com.m7mdkamal.model.JavaFile;
import com.sun.deploy.uitoolkit.impl.fx.Utils;
import com.sun.deploy.xdg.BaseDir;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.File;
import java.io.IOException;
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

    private String baseDir = "/home/mohamed/testmvn/client/";
    private String userDir;
    private String algoDir;

    public Algorithm(String username, String algoname) {
        this.username = username;
        this.algoname = algoname;
        this.btool = new Maven(username, algoname);
        this.userDir = this.baseDir + username + "/";
        this.algoDir = this.userDir + algoname + "/";
    }

    public Algorithm(String username, String algoname, BuildTool btool) {
        this.username = username;
        this.algoname = algoname;
        this.btool = btool;
    }


    public void init() {
        // build the sh file
        // cd to the basedir
        // execute the command
        File parent = new File(this.baseDir + username);
//        File.createTempFile(baseDir,"",file);
        parent.mkdir();
        try {
            File file = new File(parent.getAbsolutePath() + "/init.sh");
            file.createNewFile();
            file.setExecutable(true);
            FileUtils.writeStringToFile(file, getBaseDirCommand());

            FileUtils.writeStringToFile(file, "export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.91-6.b14.fc23.x86_64\n", true);
            FileUtils.writeStringToFile(file, this.btool.generate(), true);


            CommandLine cmdLine = new CommandLine(file);
            DefaultExecutor exec = new DefaultExecutor();
            exec.execute(cmdLine);
        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    // ~/testmvn/client/mohammed/facedetection/target/classes 
    // java  -classpath . mohammed.App mohammed/App.class

    public String run(String input) throws IOException {
        List<String> lines = new ArrayList<>();

        lines.add("cd " + this.algoDir + "target/classes");
        lines.add("java  -classpath . "+username+".App " + input);
        File file = null;
        file = createTempFile(lines);

        System.out.println(FileUtils.readFileToString(file));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommandLine cmdLine = new CommandLine(file);
        DefaultExecutor exec = new DefaultExecutor();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        exec.setStreamHandler(streamHandler);
        exec.execute(cmdLine);
        return outputStream.toString();
    }

    public void addClass() {
    }


    public void updateDependency() {
    }

    public void compile() throws IOException {
        List<String> lines = new ArrayList<>();

        lines.add("cd " + this.algoDir);
        lines.add(btool.compile());
        File file = null;
        file = createTempFile(lines);

        System.out.println(FileUtils.readFileToString(file));

        CommandLine cmdLine = new CommandLine(file);
        DefaultExecutor exec = new DefaultExecutor();
        exec.execute(cmdLine);


    }

    private String getBaseDirCommand() {
        return "cd " + this.baseDir + username + ";\n";
    }

    private File createTempFile(List<String> lines) throws IOException {
        // TODO: 6/11/16 delete it.
        lines.add(0, "export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.91-6.b14.fc23.x86_64");

        File tempFile = File.createTempFile("tmp", ".sh");
        tempFile.setExecutable(true);
        for (String line : lines) {
            FileUtils.writeStringToFile(tempFile, line + "\n", true);
        }
        return tempFile;
    }

    public ArrayList<JavaFile> getFiles() throws IOException {
        List<String> lines = new ArrayList<>();

        lines.add("cd " + this.algoDir +"src/main/java/"+username);
        lines.add("ls");
        File file = null;
        file = createTempFile(lines);

        System.out.println(FileUtils.readFileToString(file));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        CommandLine cmdLine = new CommandLine(file);
        DefaultExecutor exec = new DefaultExecutor();
        PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
        exec.setStreamHandler(streamHandler);
        exec.execute(cmdLine);
        String linesArr[] = outputStream.toString().split("\\r?\\n");
        ArrayList<JavaFile> jf = new ArrayList<>();
        for (String l : linesArr ){
            jf.add(new JavaFile(l, UUID.randomUUID().toString()));
        }

        return jf;
    }

}
