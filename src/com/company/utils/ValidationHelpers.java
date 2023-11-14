package com.company.utils;

public class ValidationHelpers {

    private static final String INCORRECT_LENGTH_MESSAGE = "%s should be between %d and %d symbols";

    public static void validateStringLength(String stringToValidate, int min, int max, String type) {
        if (stringToValidate.length() < min || stringToValidate.length() > max) {
            throw new IllegalArgumentException(INCORRECT_LENGTH_MESSAGE.formatted(type,min, max));
        }
    }
}
