package com.m7mdkamal.commandline;

/**
 * Created by Mohammed on 6/11/2016.
 */
public class Maven {

    public void createMavenApp(){

        Executor executor =
                new Executor("mvn archetype:create " +
                "-DgroupId=username " +
                "-DartifactId=test " +
                "-DarchetypeArtifactId=maven-archetype-quickstart");
        executor.execute();

    }


}
