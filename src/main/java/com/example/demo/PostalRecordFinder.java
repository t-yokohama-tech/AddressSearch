package com.example.demo;

import org.springframework.stereotype.Component;
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PostalRecordFinder {

    private final FileStream fileStream = new FileStream();

    public List<PostalRecord> find(String keyword) {

        return fileStream.fileFind()
                .map(file -> {
                    try (var parser = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
                        return parser.getRecords().get(0);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(record ->
                        new PostalRecord(
                                record.get(5), record.get(6), record.get(7), record.get(8)
                        )
                )
                .filter(record -> record.choikiKana().startsWith(keyword))
                .collect(Collectors.toList());
    }
}
