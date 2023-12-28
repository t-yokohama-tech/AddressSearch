package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileStreamTest {

    private final String keyword = "アミ";

    private final File file1 = mock(File.class);
    private final File file2 = mock(File.class);
    private final File file3 = mock(File.class);
    private final  File[] files = {file1,file2,file3};

    private final String fileName1 = "fileName1";
    private final String fileName2 = "fileName2";
    private final String fileName3 = "fileName3";
    private final Stream<String> indicesFileStream = Stream.of(fileName1,fileName2,fileName3);


    private final Indices indices = mock(Indices.class);
    {
        try {
            doReturn(indicesFileStream).when(indices).read(any());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final DataFileNameToFileFunction dataFileNameToFileFunction = mock(DataFileNameToFileFunction.class);
    {
        doReturn(file1).when(dataFileNameToFileFunction).apply(fileName1);
        doReturn(file2).when(dataFileNameToFileFunction).apply(fileName2);
        doReturn(file3).when(dataFileNameToFileFunction).apply(fileName3);
    }

    private final FileStream target = new FileStream(indices, dataFileNameToFileFunction);

    @Nested
    class iterate {

        @Test
        void returnStreamFile() throws IOException {
            var result = target.iterate(keyword);

            var ex = Stream.of(files);

            assertEquals(ex, result);

            verify(indices).read(keyword);
            verify(dataFileNameToFileFunction).apply(fileName1);
            verify(dataFileNameToFileFunction).apply(fileName2);
            verify(dataFileNameToFileFunction).apply(fileName3);
        }
    }
}
