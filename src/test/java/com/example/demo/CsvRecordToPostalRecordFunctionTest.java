package com.example.demo;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CsvRecordToPostalRecordFunctionTest {

    private final CsvRecordToPostalRecordFunction target = new CsvRecordToPostalRecordFunction();

    private final CSVRecord record = mock(CSVRecord.class);
    {
        doReturn("イカニケイサイガナイバアイ").when(record).get(5);
        doReturn("埼玉県").when(record).get(6);
        doReturn("川越市").when(record).get(7);
        doReturn("以下に掲載がない場合").when(record).get(8);
    }

    @Nested
    class apply {

        @Test
        void returnPostalRecord() {
            var result = target.apply(record);

            assertEquals(result,new PostalRecord(record.get(5), record.get(6), record.get(7), record.get(8)));


        }
    }
}
