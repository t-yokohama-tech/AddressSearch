package com.example.demo;

import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CsvRecordToPostalRecordFunction implements Function<CSVRecord, PostalRecord> {

    @Override
    public PostalRecord apply(CSVRecord record) {

        return new PostalRecord(
                record.get(5), record.get(6), record.get(7), record.get(8)
        );
    }
}

