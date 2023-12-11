package com.example.demo;

import java.util.function.BiPredicate;

public class PostalRecordPredicate implements BiPredicate<String,String> {

    @Override
    public boolean test(String choikiKana,String keyword) {

        return choikiKana.startsWith(keyword);
    }
}
