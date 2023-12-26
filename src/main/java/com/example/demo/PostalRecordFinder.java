package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.IOException;
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
    ) {
        this.fileStream = fileStream;
        this.fileToCsvRecordFunction = fileToCsvRecordFunction;
        this.csvRecordToPostalRecordFunction = csvRecordToPostalRecordFunction;
    }

    public List<PostalRecord> find(String keyword) throws IOException {

        System.out.println("filestream:" + fileStream);

        return fileStream.iterate(keyword)
                .map(fileToCsvRecordFunction)
                .map(csvRecordToPostalRecordFunction)
                .toList();
    }
}
