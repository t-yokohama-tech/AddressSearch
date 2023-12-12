package com.example.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileToCsvRecordFunctionTest {

    private final FileToCsvRecordFunction target = new FileToCsvRecordFunction();

    private final File file = mock(File.class);

    private final CSVRecord record = mock(CSVRecord.class);

    {
        doReturn("イカニケイサイガナイバアイ").when(record).get(5);
        doReturn("埼玉県").when(record).get(6);
        doReturn("川越市").when(record).get(7);
        doReturn("以下に掲載がない場合").when(record).get(8);
    }

    private final List<CSVRecord> records = List.of(record);

    private final CSVParser csvParser = mock(CSVParser.class);
    {
        try {
            doReturn(records).when(csvParser).getRecords();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Nested
    class apply {
        @Test
        void returnCsvRecord() {
            try (MockedStatic<CSVParser> mockedCSVParser = mockStatic(CSVParser.class)) {

                mockedCSVParser.when(() -> CSVParser.parse(any(File.class),any(Charset.class), any(CSVFormat.class)))
                        .thenReturn(csvParser);

                var result = target.apply(file);

                assertEquals(result, record);

                mockedCSVParser.verify(()->CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.DEFAULT));

            }
        }
        @Test
        void throwsException() {
            try (MockedStatic<CSVParser> mockedCSVParser = mockStatic(CSVParser.class)) {

                mockedCSVParser.when(() -> CSVParser.parse(any(File.class), any(Charset.class), any(CSVFormat.class)))
                        .thenThrow(new IOException());

                assertThrows(RuntimeException.class, () -> target.apply(file));
            }
        }
    }
}
