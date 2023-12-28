package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public class Indices {
    private final File indicesFile;


    public Indices(File indicesDir) {
        this.indicesFile = indicesDir;
    }

    public Stream<String> read(String keyword) throws IOException {
        Path indicesPath =
                new File(indicesFile , keyword.substring(0,2)).toPath();
        try {
            var fileNames = Files.readAllLines(indicesPath);
            return fileNames.stream();
        } catch (FileNotFoundException e) {
            return Stream.empty();
        }
    }
}