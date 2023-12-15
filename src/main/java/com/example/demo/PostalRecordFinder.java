package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostalRecordFinder {

    private final FileStream fileStream;
    private final FileToCsvRecordFunction fileToCsvRecordFunction;
    private final CsvRecordToPostalRecordFunction csvRecordToPostalRecordFunction;

    public PostalRecordFinder(
            FileStream fileStream,
            FileToCsvRecordFunction fileToCsvRecordFunction,
            CsvRecordToPostalRecordFunction csvRecordToPostalRecordFunction
    ){
        this.fileStream = fileStream;
        this.fileToCsvRecordFunction = fileToCsvRecordFunction;
        this.csvRecordToPostalRecordFunction = csvRecordToPostalRecordFunction;
    }

    public List<PostalRecord> find(String keyword) {

        return fileStream.iterate()
                .map(fileToCsvRecordFunction)
                .map(csvRecordToPostalRecordFunction)
                .filter(new PostalRecordKeywordMatchPredicate(keyword))
                .toList();
    }
}
