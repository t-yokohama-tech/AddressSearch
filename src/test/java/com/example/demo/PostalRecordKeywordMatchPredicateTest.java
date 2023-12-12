package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostalRecordKeywordMatchPredicateTest {

    private final String keyword = "アサ";
    private final PostalRecordKeywordMatchPredicate target = new PostalRecordKeywordMatchPredicate(keyword);

    @Nested
    class test {

        @Test
        void returnTrue(){
            var choikiKana = "アサヒガオカ";
            var postalRecord = new PostalRecord(choikiKana,"","","");
            assertTrue(target.test(postalRecord));
        }

        @Test
        void returnFalse(){
            var choikiKana = "ニシマチ";
            var postalRecord = new PostalRecord(choikiKana,"","","");
            assertFalse(target.test(postalRecord));
        }
    }
}
