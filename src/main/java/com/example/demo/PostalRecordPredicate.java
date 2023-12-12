package com.example.demo;


import java.util.function.Predicate;

public class PostalRecordPredicate implements Predicate<String> {

    private final String verifyKeyword;

    public PostalRecordPredicate(String vword){
        verifyKeyword = vword;
    }

    @Override
    public boolean test(String choikiKana) {

        return choikiKana.startsWith(verifyKeyword);
    }
}
