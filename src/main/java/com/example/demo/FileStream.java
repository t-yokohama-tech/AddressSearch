package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

@Component
public class FileStream {

    private final Indices indices;

    private final DataFileNameToFileFunction dataFileNameToFileFunction;

    public FileStream(
            Indices indicesDir,
            DataFileNameToFileFunction dataFileNameToFileFunction) {
        this.indices = indicesDir;
        this.dataFileNameToFileFunction = dataFileNameToFileFunction;
    }

    public Stream<File> iterate(String keyword) throws IOException {

        var indicesFileStream = indices.read(keyword);

        return indicesFileStream
                .map(dataFileNameToFileFunction);
    }
}
