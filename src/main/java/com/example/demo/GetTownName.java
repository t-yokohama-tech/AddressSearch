package com.example.demo;

import org.springframework.stereotype.Component;
import org.apache.commons.csv.*;

import java.io.*;
import java.util.Set;

@Component
public class GetTownName {

    private final SearchTownName searchTownName = new SearchTownName();

    private final String dir_path = "/Users/yokohama/AddressSearch/dataset/";

    private final String extension = ".csv";

    public Set<String> getCsvInfo(String keyword) throws IOException {

        File dir = new File(dir_path);
        File[] files = dir.listFiles();

        for (File file : files) {

            try (
                    CSVParser parser = CSVFormat
                            .DEFAULT
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
                            .withFirstRecordAsHeader()
                            .parse(new BufferedReader(new InputStreamReader(new FileInputStream(file))))) {
//                            .parse(new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(csvFilePath))))) {


                CSVRecord record = parser.getRecords().get(0);
                if (searchTownName.search(record.get(4), keyword)) {
                    return Set.of(record.get(5), record.get(6), record.get(7), record.get(8));
                }

            }

        }
        return null;
    }
}
