package com.company.utils;

public class ValidationHelpers {

    public static void validateStringLength(String stringToValidate, int min, int max, String message) {
        if (stringToValidate.length() < min || stringToValidate.length() > max) {
            throw new IllegalArgumentException(message);
        }
    }
}
