package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostalRecordFinder {

    private final FileStream fileStream;
    private final FileToCsvRecordFunction fileToCsvRecordFunction;
    private final CsvRecordToPostalRecordFunction csvRecordToPostalRecordFunction;
    private final PostalRecordKeywordMatchPredicateFactory postalRecordKeywordMatchPredicateFactory;

    public PostalRecordFinder(
            FileStream fileStream,
            FileToCsvRecordFunction fileToCsvRecordFunction,
            CsvRecordToPostalRecordFunction csvRecordToPostalRecordFunction,
            PostalRecordKeywordMatchPredicateFactory postalRecordKeywordMatchPredicateFactory
    ){
        this.fileStream = fileStream;
        this.fileToCsvRecordFunction = fileToCsvRecordFunction;
        this.csvRecordToPostalRecordFunction = csvRecordToPostalRecordFunction;
        this.postalRecordKeywordMatchPredicateFactory = postalRecordKeywordMatchPredicateFactory;
    }

    public List<PostalRecord> find(String keyword) {

        return fileStream.iterate()
                .map(fileToCsvRecordFunction)
                .map(csvRecordToPostalRecordFunction)
                .filter(postalRecordKeywordMatchPredicateFactory.create(keyword))
                .toList();
    }
}
