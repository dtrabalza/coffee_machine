package com.coffee.utils;

public class Validator {

    private final static int MAX_STRING_LENGTH = 30;

    public static void validateString(String s) {
        if (null == s || "".equals(s)) {
            throw new IllegalArgumentException("Invalid ingredient name (name null or empty)");
        }
        if (MAX_STRING_LENGTH < s.length()) {
            throw new IllegalArgumentException("Invalid ingredient name (name too long)");
        }
        if (!s.matches("[a-zA-Z]+")) {
            throw new IllegalArgumentException("Invalid ingredient name (name not valid)");
        }
    }
}
