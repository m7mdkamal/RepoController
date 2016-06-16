package com.m7mdkamal.algorithm;

/**
 * Created by mohamed on 6/16/16.
 */
public class Result {
    private Status status;
    private String log;
    private Exception exception;

    Result(Status status, String log) {
        this.log = log;
        this.status = status;
    }

    Result(Status status, String log, Exception exception) {
        this.log = log;
        this.status = status;
        this.exception = exception;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", log='" + log + '\'' +
                ", exception=" + exception +
                '}';
    }
}

