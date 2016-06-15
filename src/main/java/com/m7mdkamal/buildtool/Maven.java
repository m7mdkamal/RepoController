package com.m7mdkamal.buildtool;

/**
 * Created by mohamed on 6/11/16.
 */
public class Maven implements BuildTool{

    //mvn archetype:generate
    // -DgroupId=username
    // -DartifactId=test
    // -DarchetypeArtifactId=maven-archetype-quickstart
    // -B -DarchetypeCatalog=local

    private String groudId = "username";
    private String artifactId= "test";
    private String archetypeArtifactId = "maven-archetype-quickstart";
    private String archetypeCatalog = "local";


    public Maven(String username, String algoname) {
        this.groudId = username;
        this.artifactId = algoname;
    }

    /* generates "mvn archetype:generate -B
         * -DgroupId=username
         * -DartifactId=test
         * -DarchetypeArtifactId=maven-archetype-quickstart
         * -DarchetypeCatalog=local"
         */
    @Override
    public String generate() {

        String command = "mvn archetype:generate -B ";
        command += "-DgroupId="+groudId+" ";
        command += "-DartifactId="+artifactId+" ";
        command += "-DarchetypeArtifactId="+archetypeArtifactId+" ";
        command += "-DarchetypeCatalog="+archetypeCatalog+" ";

        return command;
    }

    @Override
    public String validate() {
        return null;
    }

    @Override
    public String compile() {
        return "mvn compile";
    }

    @Override
    public String install() {
        return "mvn install";
    }

    @Override
    public String clean() {
        return "mvn clean";
    }


}
