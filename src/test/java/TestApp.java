/**
 * Created by mohamed on 6/16/16.
 */

import com.m7mdkamal.algorithm.Algorithm;
import com.m7mdkamal.algorithm.Result;
import com.m7mdkamal.algorithm.Status;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.constraints.AssertTrue;
import java.util.Properties;

public class TestApp {

    String username = "Username";
    String algoname = "Algorname";
    Algorithm algo;

    @Before
    public void createAlgorithmObject() {
        algo = new Algorithm(username, algoname);
    }

    @After
    public void deleteAlgorithm() {
        algo.delete();
    }

    @Test
    public void testInitDelete() {
        Result result = algo.init();
        System.out.println(result.toString());
        Assert.assertEquals(Status.SUCCESS, result.getStatus());
        result = algo.init();
        Assert.assertEquals(Status.FAILURE, result.getStatus());
    }

    @Test
    public void testCompile() {
        Result result = algo.init();
        Assert.assertEquals(Status.SUCCESS, result.getStatus());

        //test with javac
        result = algo.compile(false);
        System.out.println(result.toString());
        Assert.assertEquals(Status.SUCCESS, result.getStatus());

        //test with maven
        result = algo.compile();
        System.out.println(result.toString());
        Assert.assertEquals(Status.SUCCESS, result.getStatus());
    }

    @Test
    public void testUpdateFile() {


        Result result = algo.init();
        Assert.assertEquals(Status.SUCCESS, result.getStatus());

        algo.updateFile("App.java", "package Username;\n" +
                "\n" +
                "/**\n" +
                " * Hello world!\n" +
                " *\n" +
                " */\n" +
                "public class App \n" +
                "{\n" +
                "    public static void main( String[] args )\n" +
                "    {\n" +
                "        System.out.println( \"Hello me!\" );\n" +
                "    }\n" +
                "}\n");

        result = algo.compile();
        System.out.println(result.toString());
        Assert.assertEquals(Status.SUCCESS, result.getStatus());

    }

    @Test
    public void testRun() {
        algo.init();
        algo.compile();
        Result result = algo.run("");
        System.out.println(result.toString());
        Assert.assertEquals(Status.SUCCESS, result.getStatus());
    }

    @Test
    public void testUpdateDependency() {
        algo.init();
        Result result = algo.updateDependency("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "  xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n" +
                "  <modelVersion>4.0.0</modelVersion>\n" +
                "  <groupId>Username</groupId>\n" +
                "  <artifactId>Algorname</artifactId>\n" +
                "  <packaging>jar</packaging>\n" +
                "  <version>1.0-SNAPSHOT</version>\n" +
                "  <name>Algorname</name>\n" +
                "  <url>http://maven.apache.org</url>\n" +
                "  <dependencies>\n" +
                "  </dependencies>\n" +
                "</project>");
//        algo.compile();

        System.out.println(result.toString());
        Assert.assertEquals(Status.SUCCESS, result.getStatus());
    }


}