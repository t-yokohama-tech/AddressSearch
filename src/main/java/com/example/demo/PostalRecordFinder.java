package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PostalRecordFinder {

    private final FileStream fileStream = new FileStream();

    public List<PostalRecord> find(String keyword) {

        return fileStream.iterate()
                .map(file -> new PostalRecordTypeConversionFunction().apply(new GetRecordFunction().apply(file)))
                .filter(record -> new PostalRecordPredicate().test(record.choikiKana(),keyword))
                .collect(Collectors.toList());
    }
}
