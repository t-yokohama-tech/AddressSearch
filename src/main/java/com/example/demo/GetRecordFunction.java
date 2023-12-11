package com.example.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;

public class GetRecordFunction implements Function<File,CSVRecord> {

    @Override
    public CSVRecord apply(File file){

         try (var parser = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.DEFAULT)) {
            return parser.getRecords().get(0);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
