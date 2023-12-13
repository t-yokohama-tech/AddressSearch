package com.example.demo;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.stream.Stream;

public class FileStream {


    private final File file;
    private final FileFilter fileFilter;

    public FileStream(File datasetDir, FileFilter dataFileFilter){
        this.fileFilter = dataFileFilter;
        this.file = datasetDir;
    }

    public Stream<File> iterate(){
        return Stream.of(Objects.requireNonNull(file.listFiles(fileFilter)));
    }
}
