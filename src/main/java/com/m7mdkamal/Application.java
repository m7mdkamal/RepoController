package com.m7mdkamal;

import com.m7mdkamal.algorithm.Algorithm;
import com.m7mdkamal.buildtool.BuildTool;
import com.m7mdkamal.buildtool.Maven;
import com.m7mdkamal.commandline.Executor;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        System.out.println("Hello World");

//        BuildTool maven = new Maven();

        Algorithm algo = new Algorithm("mahd", "algonamee");
//        ArrayList<JavaFile> jfs = algo.getFiles();
//        for (JavaFile jf : jfs)
//            System.out.println(jf.getFileName()+" "+jf.getId());
//        System.out.println(.toString());
        algo.init();
        algo.compile();
        System.out.println(algo.run(""));
//
//        ProcessBuilder pb = new ProcessBuilder();
//
//
//        Executor e = new Executor("cd /home/mohamed/testmvn;ls");
//        System.out.println(e.execute());


//        CommandLine cmdLine = new CommandLine("ls");
//        cmdLine.addArgument("-l");
//        DefaultExecutor exec = new DefaultExecutor();
//        exec.execute(cmdLine);

    }
}