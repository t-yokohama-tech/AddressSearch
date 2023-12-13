package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileStreamTest {

    private final File file1 = mock(File.class);
    private final File file2 = mock(File.class);
    private final File file3 = mock(File.class);
    private final  File[] files = {file1,file2,file3};

    private final File fileDir = mock(File.class);
    private final FileFilter fileFilter = mock(FileFilter.class);

    {
        doReturn(files).when(fileDir).listFiles(any(FileFilter.class));
    }

    private final FileStream target = new FileStream(fileDir, fileFilter);

    @Nested
    class iterate {

        @Test
        void returnStreamFile() {
            var result = target.iterate();

            assertEquals(Stream.of(files), result);

            verify(fileDir).listFiles(fileFilter);
        }
    }
}
