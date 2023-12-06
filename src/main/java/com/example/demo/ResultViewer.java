package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ResultViewer {

    public void output(Set<String> info){
        info.forEach(System.out::println);

    }
}
