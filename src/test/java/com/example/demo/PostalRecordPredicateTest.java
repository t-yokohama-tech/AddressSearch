package com.example.demo;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PostalRecordPredicateTest {

    private final PostalRecordPredicate target = new PostalRecordPredicate();

    @Nested
    class test {

        @Test
        void returnTrue(){
            var choikiKana = "アサヒガオカ";
            var keyword = "アサ";
            assertTrue(target.test(choikiKana,keyword));
        }

        @Test
        void returnFalse(){
            var choikiKana = "ニシマチ";
            var keyword = "アサ";
            assertFalse(target.test(choikiKana,keyword));
        }
    }
}
