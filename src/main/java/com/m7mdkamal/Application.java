package com.m7mdkamal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import java.io.IOException;


@SpringBootApplication
public class Application extends SpringBootServletInitializer{

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }

}