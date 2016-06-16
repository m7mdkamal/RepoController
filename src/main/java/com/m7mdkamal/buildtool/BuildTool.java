package com.m7mdkamal.buildtool;

/**
 * Created by mohamed on 6/11/16.
 */
public interface BuildTool {

    String generate();
    String validate();
    String compile();
    String install();
    String clean();
    String updateDependency();
}
