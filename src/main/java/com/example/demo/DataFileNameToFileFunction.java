package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.util.function.Function;

@Component
public class DataFileNameToFileFunction implements Function<String, File> {

    private final File datasetDir;

    public DataFileNameToFileFunction(
            File datasetDir
    ) {
        this.datasetDir = datasetDir;
    }

    @Override
    public File apply(String fileName) {
        System.out.println("datasetDir+fileName:"+new File(datasetDir  +"/"+ fileName));
        return new File(datasetDir  +"/"+ fileName).toPath().toFile();
    }
}
