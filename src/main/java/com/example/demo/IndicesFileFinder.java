package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class IndicesFileFinder {
    private final File indicesFile;


    public IndicesFileFinder(File indicesDir) {
        this.indicesFile = indicesDir;
    }

    public Stream<String> indicesFileGet(String keyword) throws IOException {

        Path in = Path.of(indicesFile + "/" + keyword.substring(0,2));

        try {
            var fileNames = Files.readAllLines(in);
            return fileNames.stream();
        } catch (IOException e) {
            System.out.println("該当ファイルなし");
            System.exit(0);
        }
        return null;
    }
}