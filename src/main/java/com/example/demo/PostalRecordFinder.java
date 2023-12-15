package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostalRecordFinder {

    private final FileStream fileStream;
    private final FileToCsvRecordFunction fileToCsvRecordFunction;
    private final CsvRecordToPostalRecordFunction csvRecordToPostalRecordFunction;

    private final PostalRecordKeywordMatchPredicate postalRecordKeywordMatchPredicate;

    public PostalRecordFinder(
            FileStream fileStream,
            FileToCsvRecordFunction fileToCsvRecordFunction,
            CsvRecordToPostalRecordFunction csvRecordToPostalRecordFunction,
            PostalRecordKeywordMatchPredicate keywordMatchPredicate
    ){
        this.fileStream = fileStream;
        this.fileToCsvRecordFunction = fileToCsvRecordFunction;
        this.csvRecordToPostalRecordFunction = csvRecordToPostalRecordFunction;
        this.postalRecordKeywordMatchPredicate = keywordMatchPredicate;
    }

    public List<PostalRecord> find(String keyword) {

        return fileStream.iterate()
                .map(fileToCsvRecordFunction)
                .map(csvRecordToPostalRecordFunction)
                .filter(postalRecordKeywordMatchPredicate)
                .toList();
    }
}
