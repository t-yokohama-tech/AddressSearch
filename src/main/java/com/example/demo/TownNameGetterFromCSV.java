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
        File[] files = dir.listFiles();

        Set<String> matchList = new HashSet<>();

        for (File file : Objects.requireNonNull(files)) {

            try (
                    CSVParser parser = CSVFormat
                            .DEFAULT
                            .withQuote(null)
                            .withHeader(
                                    "NationalLocalGovernmentCode",
                                    "OldPostalCode",
                                    "PostalCode",
                                    "NameOfPrefectures_Kana",
                                    "CityName_Kana",
                                    "TownAreaName_Kana",
                                    "NameOfPrefectures_ChinaChara",
                                    "CityName_ChinaChara",
                                    "TownAreaName_ChinaChara",
                                    "Flg1",
                                    "Flg2",
                                    "Flg3",
                                    "Flg4",
                                    "ViewUpdates",
                                    "ReasonForChange")
                            .parse(new BufferedReader(new InputStreamReader(new FileInputStream(file))))) {


                CSVRecord record = parser.getRecords().get(0);
                System.out.println(record.get(0));
                if (townNameSearcher.search(record.get(5), keyword)) {

                    matchList.add(record.get(5) + "," + record.get(6) + "," + record.get(7) + "," + record.get(8));
                    System.out.println(record.get(5) + "," + record.get(6) + "," + record.get(7) + "," + record.get(8));
                }

            }

        }
        return matchList;
    }
}
