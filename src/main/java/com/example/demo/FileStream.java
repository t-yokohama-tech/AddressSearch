package com.example.demo;

import java.io.File;
import java.util.Objects;
import java.util.stream.Stream;

public class FileStream {
    private final String dir_path = "/Users/yokohama/AddressSearch/dataset/";

    public Stream<File> fileFinder(){
        Stream.Builder<File> builder = Stream.builder();

        File dir = new File(dir_path);
        File[] files = dir.listFiles(new FileFilterExtension());

        for(File file: Objects.requireNonNull(files)){
            builder.add(file);
        }

        return builder.build();
    }
}
