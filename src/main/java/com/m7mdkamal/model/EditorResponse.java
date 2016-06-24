package com.m7mdkamal.model;

import com.m7mdkamal.algorithm.Status;

/**
 * Created by mohamed on 6/23/16.
 */
public class EditorResponse {
    private Status status;
    private String data;

    public EditorResponse(Status status, String data) {
        this.status = status;
        this.data = data;
    }

    public EditorResponse(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
