package com.example.demo;

import java.io.File;
import java.util.Objects;
import java.util.stream.Stream;

public class FileStream {
    private final String dir_path = "/Users/yokohama/AddressSearch/dataset/";

    private final FileFilterExtension fileFilterExtension = new FileFilterExtension();

    public Stream<File> fileFind(){
        return Stream.of(Objects.requireNonNull( new File(dir_path).listFiles(fileFilterExtension)));
    }
}
