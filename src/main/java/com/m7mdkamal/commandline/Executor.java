package com.m7mdkamal.commandline;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Mohammed on 6/11/2016.
 */
public class Executor {

    private String command;

    public Executor() {
    }

    public Executor(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public Executor setCommand(String command) {
        this.command = command;
        return this;
    }

    public String execute(){
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(this.command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();
    }
}
