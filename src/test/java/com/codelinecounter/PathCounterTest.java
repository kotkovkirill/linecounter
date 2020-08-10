package com.codelinecounter;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class PathCounterTest {

    private PathCounter pathCounter = new PathCounter();

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void testPathCounter() throws Exception {
        File root = temporaryFolder.newFolder("root");
        File javaFile1 = new File(root, "TestGeneric1.java");
        FileUtils.copyInputStreamToFile(this.getClass().getClassLoader().getResourceAsStream("TestGeneric1.java"), javaFile1);

        File child = temporaryFolder.newFolder("root", "childfolder");
        File javaFile2 = new File(child, "TestGeneric2.java");
        FileUtils.copyInputStreamToFile(this.getClass().getClassLoader().getResourceAsStream("TestGeneric2.java"), javaFile2);

        StringBuilder sb = new StringBuilder();
        pathCounter.apply(root.getAbsolutePath(), "", sb);
        System.out.println(sb);
        assertTrue(sb.toString().contains("root : 8"));
    }
}
