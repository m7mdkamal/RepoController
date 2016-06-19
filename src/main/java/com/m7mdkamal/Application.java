package com.m7mdkamal;

import com.m7mdkamal.algorithm.Algorithm;
import com.m7mdkamal.buildtool.BuildTool;
import com.m7mdkamal.buildtool.Maven;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
        System.out.println("APP STARTS HERE");
    }
}