package com.example.demo;

import java.io.File;
import java.io.FileFilter;

public class FileFilterExtension implements FileFilter {

    @Override
    public boolean accept(File pathname){
        return pathname.getName().startsWith("r-");
    }
}
