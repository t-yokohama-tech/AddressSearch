package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.util.*;

@Component
public class PostalRecordFinder {

    private final FileStream fileStream;

    public PostalRecordFinder(FileStream fileStream){
        this.fileStream = fileStream;
    }

    public List<PostalRecord> find(String keyword) {

        return fileStream.iterate()
                .map(new FileToCsvRecordFunction())
                .map(new CsvRecordToPostalRecordFunction())
                .filter(new PostalRecordKeywordMatchPredicate(keyword))
                .toList();
    }
}
