package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class IndicesFileFinder {
//ここでindicesフォルダから読み取り対象ファイル名を取得

    private final Path dir_indices = Paths.get("/Users/yokohama/AddressSearch/data/indices/");

    private final File indicesFile;


    public IndicesFileFinder(File indicesDir) {
        this.indicesFile = indicesDir;
    }

    public Stream<String> indicesFileGetter(String keyword) throws IOException {

        Path in = Path.of(indicesFile + "/" + keyword);

        var fileNames = Files.readAllLines(in);
        return fileNames.stream();
    }

}
