package com.example.demo;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class Config {

    private final String dir_path = "/Users/yokohama/AddressSearch/dataset/";

    @Bean
    public File datasetDir(){
        return new File(dir_path);
    }

    @Bean
    public PostalRecordKeywordMatchPredicate keywordMatchPredicate(String keyword)  {
        return new PostalRecordKeywordMatchPredicate(keyword);
    }
}
