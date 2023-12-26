package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

@Component
public class FileFilterExtension implements  FilenameFilter {

    @Override
    public boolean accept(File pathname,String fileName){
        return pathname.getName().startsWith(fileName);
    }
}
