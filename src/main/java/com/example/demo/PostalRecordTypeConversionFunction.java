package com.example.demo;

import org.apache.commons.csv.CSVRecord;

import java.util.function.Function;

public class PostalRecordTypeConversionFunction implements Function<CSVRecord, PostalRecord> {

    @Override
    public PostalRecord apply(CSVRecord record) {

        return new PostalRecord(
                record.get(5), record.get(6), record.get(7), record.get(8)
        );
    }
}

