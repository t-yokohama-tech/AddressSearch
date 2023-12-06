package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Arrays;

@SpringBootApplication
@AllArgsConstructor
public class AddressSearch  {
    public static void main(String[] args) {
        SpringApplication.run(AddressSearch.class, args);
    }

    private final GetTownName getTownName;//CSVから抜き出し

    private final ResultOutput resultOutput; //結果出力

    public void run(String... args) throws IOException {

        System.out.println(Arrays.toString(args));
        resultOutput.output(getTownName.getCsvInfo(args[0]));
    }
}