package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@AllArgsConstructor
public class AddressFinder implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AddressFinder.class, args);
    }

    private final PostalRecordFinder townNameGetterFromCSV;//CSVから抜き出し

    private final ResultViewer resultViewer; //結果出力

    @Override
    public void run(String... args) {

        resultViewer.output(townNameGetterFromCSV.find(args[0]));
    }
}