package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
@AllArgsConstructor
public class AddressFinder implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AddressFinder.class, args);
    }

    private final TownNameGetterFromCSV townNameGetterFromCSV;//CSVから抜き出し

    private final ResultViewer resultViewer; //結果出力

    @Override
    public void run(String... args) throws IOException {

        resultViewer.output(townNameGetterFromCSV.getCsvInfo(args[0]));
    }
}