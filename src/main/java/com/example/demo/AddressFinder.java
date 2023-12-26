package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
@AllArgsConstructor
public class AddressFinder implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(AddressFinder.class, args);
    }

    private final PostalRecordFinder postalRecordFinder;//CSVから住所情報抜き出し

    private final ResultViewer resultViewer; //結果出力

    @Override
    public void run(String... args) throws IOException {

        resultViewer.output(postalRecordFinder.find(args[0]));
    }
}