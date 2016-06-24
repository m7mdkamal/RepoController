package com.m7mdkamal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.m7mdkamal.algorithm.Algorithm;
import com.m7mdkamal.algorithm.Result;
import com.m7mdkamal.algorithm.Status;
import com.m7mdkamal.model.EditorResponse;
import com.m7mdkamal.model.JavaFile;
import org.springframework.web.bind.annotation.*;

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
        return "SD:"+ username;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/files" , method = RequestMethod.GET)
    public EditorResponse getFiles() throws IOException {
        Algorithm algo = new Algorithm("aa", "algo");
        Result result = algo.getFiles();
        if(result.getStatus().equals(Status.SUCCESS)) {
            String linesArr[] = result.getLog().split("\\r?\\n");
            ArrayList<JavaFile> jf = new ArrayList<>();

            for (String l : linesArr) {
                jf.add(new JavaFile(l));
            }
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(jf);
            return new EditorResponse(Status.SUCCESS, json);
        }else{
            return new EditorResponse(Status.FAILURE);
        }

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/files/{filename:.+}" , method = RequestMethod.GET)
    public EditorResponse getFile(@PathVariable String filename) throws IOException {
        Algorithm algo = new Algorithm("aa", "algo");
        Result result = algo.getFile(filename);
        if(result.getStatus().equals(Status.SUCCESS)) {
            return new EditorResponse(Status.SUCCESS, result.getLog());
        }else{
            return new EditorResponse(Status.FAILURE);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/files/{filename:[a-zA-Z0-9]+\\.java}" , method = RequestMethod.POST)
    public @ResponseBody EditorResponse postFile(@PathVariable("filename") String filename,@RequestBody String body) throws IOException {

        Algorithm algo = new Algorithm("aa", "algo");
        Result result = algo.updateFile(filename , body);
        if(result.getStatus().equals(Status.SUCCESS)) {
            return new EditorResponse(Status.SUCCESS);
        }else{
            return new EditorResponse(Status.FAILURE);
        }
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/files/{filename:[a-zA-Z0-9]+\\.java}" , method = RequestMethod.DELETE)
    public EditorResponse deleteFile(@PathVariable("filename") String filename) throws IOException {
        Algorithm algo = new Algorithm("aa", "algo");
        Result result = algo.deleteFile(filename);
        if(result.getStatus().equals(Status.SUCCESS)) {
            return new EditorResponse(Status.SUCCESS);
        }else{
            return new EditorResponse(Status.FAILURE);
        }
    }
}
