package com.m7mdkamal.model;

/**
 * Created by mohamed on 6/12/16.
 */
public class JavaFile {
    private String fileName;
    private String id;

    public JavaFile(String fileName, String id) {
        this.fileName = fileName;
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
