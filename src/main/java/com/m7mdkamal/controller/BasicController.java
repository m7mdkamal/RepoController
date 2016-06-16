package com.m7mdkamal.controller;

import com.m7mdkamal.algorithm.Algorithm;
import com.m7mdkamal.algorithm.Result;
import com.m7mdkamal.model.JavaFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mohamed on 6/12/16.
 */
@RestController
public class BasicController {

    @RequestMapping("/")
    public String root(@RequestParam(value="username", defaultValue="World") String username,
                       @RequestParam(value="algoname", defaultValue="World") String algoname,
                       @RequestParam(value="init", defaultValue="0") String init ,
                       @RequestParam(value="compile", defaultValue="0") String compile ){

        Algorithm algo = new Algorithm(username, algoname);

        String s = "";
            if(init.equals("1")) {
                Result result = algo.init();
                return result.toString();
            }
            if(compile.equals("1")) {
                Result result = algo.compile();
                return result.toString();
            }
//            s = algo.run();


        return "SD:"+ username;
    }

//    @RequestMapping("/getFiles.json")
//    public ArrayList<JavaFile> getFiles() throws IOException {
//        Algorithm algo = new Algorithm("mohammed", "facedetection");
//        ArrayList<JavaFile> jfs = algo.getFiles();
//
//        return jfs;
//    }


}
