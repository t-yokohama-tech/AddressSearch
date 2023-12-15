package com.example.demo;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class PostalRecordFinderTest {

    private final String keyword = "アサ";

    private final File file1 = mock(File.class);
    private final File file2 = mock(File.class);
    private final File file3 = mock(File.class);
    private final File file4 = mock(File.class);

    private final  File[] files = {file1,file2,file3,file4};

    private final CSVRecord record1 = mock(CSVRecord.class);
    private final CSVRecord record2 = mock(CSVRecord.class);
    private final CSVRecord record3 = mock(CSVRecord.class);
    private final CSVRecord record4 = mock(CSVRecord.class);
    {
        doReturn("アサクサ").when(record1).get(5);
        doReturn("東京都").when(record1).get(6);
        doReturn("台東区").when(record1).get(7);
        doReturn("浅草").when(record1).get(8);

        doReturn("アサカマチ（シバミヤヒガシ）").when(record2).get(5);
        doReturn("福島県").when(record2).get(6);
        doReturn("郡山市").when(record2).get(7);
        doReturn("安積町（柴宮東）").when(record2).get(8);

        doReturn("アサヒチュウオウドオリ").when(record3).get(5);
        doReturn("熊本県").when(record3).get(6);
        doReturn("八代市").when(record3).get(7);
        doReturn("旭中央通").when(record3).get(8);

        doReturn("イカニケイサイガナイバアイ").when(record4).get(5);
        doReturn("埼玉県").when(record4).get(6);
        doReturn("川越市").when(record4).get(7);
        doReturn("以下に掲載がない場合").when(record4).get(8);
    }

    private final PostalRecord postalRecord1 =
            new PostalRecord(record1.get(5),record1.get(6),record1.get(7),record1.get(8));

    private final PostalRecord postalRecord2 =
             new PostalRecord(record2.get(5),record2.get(6),record2.get(7),record2.get(8));

    private final PostalRecord postalRecord3 =
            new PostalRecord(record3.get(5),record3.get(6),record3.get(7),record3.get(8));

    private final PostalRecord postalRecord4 =
            new PostalRecord(record4.get(5),record4.get(6),record4.get(7),record4.get(8));

    private final List<PostalRecord> postalRecords = List.of(postalRecord1,postalRecord2,postalRecord3);


    private final FileStream fileStream = mock(FileStream.class);
    {
        doReturn(Stream.of(files)).when(fileStream).iterate();
    }

    private final FileToCsvRecordFunction fileToCsvRecordFunction = mock(FileToCsvRecordFunction.class);
    {
        doReturn(record1).when(fileToCsvRecordFunction).apply(file1);
        doReturn(record2).when(fileToCsvRecordFunction).apply(file2);
        doReturn(record3).when(fileToCsvRecordFunction).apply(file3);
        doReturn(record4).when(fileToCsvRecordFunction).apply(file4);
    }

    private final CsvRecordToPostalRecordFunction csvRecordToPostalRecordFunction = mock(CsvRecordToPostalRecordFunction.class);
    {
        doReturn(postalRecord1).when(csvRecordToPostalRecordFunction).apply(record1);
        doReturn(postalRecord2).when(csvRecordToPostalRecordFunction).apply(record2);
        doReturn(postalRecord3).when(csvRecordToPostalRecordFunction).apply(record3);
        doReturn(postalRecord4).when(csvRecordToPostalRecordFunction).apply(record4);
    }

    private final PostalRecordFinder target = new PostalRecordFinder(
            fileStream, fileToCsvRecordFunction, csvRecordToPostalRecordFunction
    );

    @Nested
    class find {
        @Test
        void returnPostalRecords() {
            var result = target.find(keyword);

            assertEquals(postalRecords,result);

            verify(fileStream).iterate();
            
            verify(fileToCsvRecordFunction).apply(file1);
            verify(fileToCsvRecordFunction).apply(file2);
            verify(fileToCsvRecordFunction).apply(file3);
            verify(fileToCsvRecordFunction).apply(file4);

            verify(csvRecordToPostalRecordFunction).apply(record1);
            verify(csvRecordToPostalRecordFunction).apply(record2);
            verify(csvRecordToPostalRecordFunction).apply(record3);
            verify(csvRecordToPostalRecordFunction).apply(record4);
        }
    }
}
