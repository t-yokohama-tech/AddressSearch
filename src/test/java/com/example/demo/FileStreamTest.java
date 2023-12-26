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


    private final IndicesFileFinder indicesFileFinder = mock(IndicesFileFinder.class);
    {
        try {
            doReturn(indicesFileStream).when(indicesFileFinder).indicesFileGetter(any());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final FileFunction fileFunction = mock(FileFunction.class);
    {
        doReturn(file1).when(fileFunction).apply(fileName1);
        doReturn(file2).when(fileFunction).apply(fileName2);
        doReturn(file3).when(fileFunction).apply(fileName3);
    }

    private final FileStream target = new FileStream(indicesFileFinder, fileFunction);

    @Nested
    class iterate {

        @Test
        void returnStreamFile() throws IOException {
            var result = target.iterate(keyword);

            assertEquals(Stream.of(files), result);

            verify(indicesFileFinder).indicesFileGetter(keyword);
            verify(fileFunction).apply(fileName1);
            verify(fileFunction).apply(fileName2);
            verify(fileFunction).apply(fileName3);
        }
    }
}
