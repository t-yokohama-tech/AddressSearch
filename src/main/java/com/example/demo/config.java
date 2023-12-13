package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class config {

    private final String dir_path = "/Users/yokohama/AddressSearch/dataset/";

    @Bean
    public File datasetDir(){
        return new File(dir_path);
    }
}
