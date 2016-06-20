package com.m7mdkamal;

import com.m7mdkamal.algorithm.Algorithm;
import com.m7mdkamal.buildtool.BuildTool;
import com.m7mdkamal.buildtool.Maven;
import groovy.util.logging.Log4j;
import groovy.util.logging.Slf4j;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;


@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    public static void main(String[] args) throws IOException {
        File tmpFile = new File("/tmp/springboot");
        tmpFile.mkdirs();
        tmpFile = new File("/tmp/springboot/log.log");
        tmpFile.createNewFile();
        System.setOut(new PrintStream(tmpFile));
        System.out.println("HELLO ");
        SpringApplication.run(Application.class, args);
        System.out.println("BYE ");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
    }
}