package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class PostalRecordKeywordMatchPredicateFactory {

    public Predicate<PostalRecord> create(String keyword){
        return new PostalRecordKeywordMatchPredicate(keyword);
    }
}
