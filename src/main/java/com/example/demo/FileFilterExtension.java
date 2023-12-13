package com.example.demo;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileFilter;

@Component
public class FileFilterExtension implements FileFilter {

    @Override
    public boolean accept(File pathname){
        return pathname.getName().startsWith("r-");
    }
}
