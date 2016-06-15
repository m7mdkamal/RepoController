package com.m7mdkamal.controller;

import com.m7mdkamal.algorithm.Algorithm;
import com.m7mdkamal.model.JavaFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mohamed on 6/12/16.
 */
@RestController
public class BasicController {

    @RequestMapping("/")
    public String root(){
        Algorithm algo = new Algorithm("mohammed", "facedetection");
//        algo.init();
//        algo.compile();
        String s = "";
        try {
            s = algo.run("100");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "SD:"+ s;
    }

    @RequestMapping("/getFiles.json")
    public ArrayList<JavaFile> getFiles() throws IOException {
        Algorithm algo = new Algorithm("mohammed", "facedetection");
        ArrayList<JavaFile> jfs = algo.getFiles();

        return jfs;
    }


}
