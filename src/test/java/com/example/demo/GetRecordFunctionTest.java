package com.example.demo;

import org.apache.commons.csv.CSVParser;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GetRecordFunctionTest {


    private final GetRecordFunction target = new GetRecordFunction();
    private final CSVParser parser = mock(CSVParser.class);

    {
        try {
            doReturn("イカニケイサイガナイバアイ").when(parser).getRecords().get(0).get(5);
            doReturn("埼玉県").when(parser).getRecords().get(0).get(6);
            doReturn("川越市").when(parser).getRecords().get(0).get(7);
            doReturn("以下に掲載がない場合").when(parser).getRecords().get(0).get(8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private final File file = mock(File.class);
//    {
//        doReturn().when(file).
//    }

    @Nested
    class apply {
        @Test
        void returnA() throws IOException {
            var parse = parser.getRecords().get(0);

            var result = target.apply(file);


        }
    }
}
