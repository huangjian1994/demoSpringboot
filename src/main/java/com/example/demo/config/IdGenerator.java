package com.example.demo.config;

import com.google.common.base.CharMatcher;

import java.util.UUID;

public class IdGenerator {

    public static String uuid32() {
        return CharMatcher.is('-').removeFrom(UUID.randomUUID().toString());
    }

}
