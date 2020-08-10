package com.codelinecounter;

import java.io.File;
import java.io.FileReader;

/**
 * This class counts lines for both files and directories recursively and provides well-formatted result
 */
public class PathCounter {

    private static final String INDENT = "    ";

    private LineCounter lineCounter = new LineCounter();

    /**
     * This method processes given path and adds necessary information to the given stringBuilder with the given indent.
     * If path is file then only filename and line number is added,
     * if path is directory then directory content is processed recursively
     * @param path path to process
     * @param indent indent to use when printing result
     * @param stringBuilder stringBuilder to append resutl
     * @return number of lines under current path
     */
    int apply(String path, String indent, StringBuilder stringBuilder) {

        try {
            File file = new File(path);
            if(!file.exists()) {
                throw new RuntimeException("File not found " + path);
            }

            //process file case, end recursion
            if(file.isFile()) {
                int linesCount = lineCounter.countLines(new FileReader(file));
                append(stringBuilder, indent, file.getName(), linesCount);
                return linesCount;
            }

            if(file.isDirectory()) {
                File[] files = file.listFiles();

                //process empty folder case, end recursion
                if(files == null || files.length == 0) {
                    append(stringBuilder, indent, file.getName(), 0);
                    return 0;
                }

                //process not empty folder case
                int subCounter = 0;
                StringBuilder subBuilder = new StringBuilder();

                for (File subfile : files) {
                    subCounter += apply(subfile.getAbsolutePath(), indent + INDENT, subBuilder);
                }

                //now we have total number of lines in subCounter and String content to add in subBuilder
                append(stringBuilder, indent, file.getName(), subCounter)
                        .append(subBuilder);
                return subCounter;
            }
            throw new RuntimeException("Invalid file " + path);
        } catch(Exception e) {
            throw new RuntimeException("Unable to count lines in path", e);
        }
    }

    private StringBuilder append(StringBuilder stringBuilder, String indent, String name, int linesCount) {
        return stringBuilder
                .append(indent)
                .append(name)
                .append(" : ")
                .append(linesCount)
                .append(System.lineSeparator());
    }

    /**
     * Entry point for utility. Provide filename or directory name as input parameter
     * @param args
     */
    public static void main(String[] args) {
        if(args.length != 1) {
            throw new IllegalStateException("Must provide exactly one argument - filename or directory");
        }
        StringBuilder sb = new StringBuilder();
        new PathCounter().apply(args[0], "", sb);
        System.out.println(sb);
    }
}
