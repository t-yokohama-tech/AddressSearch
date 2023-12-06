package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class SearchTownName {

    public boolean search(String townName, String keyword) {

        return townName.startsWith(keyword);
    }
}
