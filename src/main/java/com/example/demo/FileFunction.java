package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.function.Function;

@Component
public class FileFunction implements Function<String, File> {

    private final String dir_path = "/Users/yokohama/AddressSearch/dataset/";

    @Override
    public File apply(String fileName) {
        // stringでファイル名を受け取り、pathを設定して、Fileを返す
        return new File(dir_path+fileName);
    }
}
