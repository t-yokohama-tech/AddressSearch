package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ResultViewer {

    public void output(List<PostalRecord> info){
        info.forEach(System.out::println);

    }
}
