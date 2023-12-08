package com.example.demo;

import org.springframework.stereotype.Component;
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PostalRecordFinder {

    private final TownNameSearcher townNameSearcher = new TownNameSearcher();

    private final FileStream fileStream = new FileStream();

    public List<PostalRecord> find(String keyword) {

        return fileStream.fileFinder()
                .map(file -> {
                    try(var parser = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
                        CSVRecord record =parser
                                        .getRecords().get(0);
                            return new PostalRecord(
                                    record.get(5), record.get(6), record.get(7), record.get(8)
                            );
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(record -> record.choikiKana().startsWith(keyword))
                .collect(Collectors.toList());
    }
}
