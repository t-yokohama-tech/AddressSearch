package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class FileStream {

    private final IndicesFileFinder indicesFileFinder;

    private final FileFunction fileFunction;

    public FileStream(
            IndicesFileFinder indicesFileFinder,
            FileFunction fileFunction) {
        this.indicesFileFinder = indicesFileFinder;
        this.fileFunction = fileFunction;
    }

    public Stream<File> iterate(String keyword) throws IOException {

        var indicesFileStream = indicesFileFinder.indicesFileGetter(keyword);

        return indicesFileStream
                .map(fileFunction);
    }
}
