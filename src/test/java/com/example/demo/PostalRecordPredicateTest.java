package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostalRecordPredicateTest {

    private final String keyword = "アサ";
    private final PostalRecordPredicate target = new PostalRecordPredicate(keyword);

    @Nested
    class test {

        @Test
        void returnTrue(){
            var choikiKana = "アサヒガオカ";
            assertTrue(target.test(choikiKana));
        }

        @Test
        void returnFalse(){
            var choikiKana = "ニシマチ";
            assertFalse(target.test(choikiKana));
        }
    }
}
