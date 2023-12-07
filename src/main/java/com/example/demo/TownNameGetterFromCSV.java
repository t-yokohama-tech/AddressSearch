package com.example.demo;

import org.springframework.stereotype.Component;
import org.apache.commons.csv.*;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class TownNameGetterFromCSV {

    private final TownNameSearcher townNameSearcher = new TownNameSearcher();

    private final String dir_path = "/Users/yokohama/AddressSearch/dataset/";


    public Set<String> getCsvInfo(String keyword) throws IOException {

        File dir = new File(dir_path);
        File[] files = dir.listFiles(new FileFilterExtension());

        Set<String> matchList = new HashSet<>();

        for (File file : Objects.requireNonNull(files)) {
            try (
                    CSVParser parser = CSVFormat
                            .DEFAULT
                            .withQuote(null)
                            .parse(new BufferedReader(new InputStreamReader(new FileInputStream(file))))) {

                CSVRecord record = parser.getRecords().get(0);
                if (townNameSearcher.search(record.get(5), "\"" + keyword)) {
                    matchList.add(record.get(5) + "," + record.get(6) + "," + record.get(7) + "," + record.get(8));
                }
            }
        }
        return matchList;
    }
}
