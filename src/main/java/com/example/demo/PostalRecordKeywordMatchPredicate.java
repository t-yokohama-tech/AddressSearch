package com.example.demo;


import java.util.function.Predicate;

public class PostalRecordKeywordMatchPredicate implements Predicate<PostalRecord> {

    private final String verifyKeyword;

    public PostalRecordKeywordMatchPredicate(String vword){
        verifyKeyword = vword;
    }

    @Override
    public boolean test(PostalRecord postalRecord) {

        return postalRecord.choikiKana().startsWith(verifyKeyword);
    }
}
