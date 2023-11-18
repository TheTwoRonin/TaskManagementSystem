package com.company.utils;

import java.util.List;

public class ValidationHelpers {

    private static final String INCORRECT_LENGTH_MESSAGE = "%s should be between %d and %d symbols";
    private static final String INVALID_NUMBER_OF_ARGUMENTS = "Invalid number of arguments. Expected: %d; received: %d.";

    public static void validateStringLength(String stringToValidate, int min, int max, String type) {
        if (stringToValidate.length() < min || stringToValidate.length() > max) {
            throw new IllegalArgumentException(INCORRECT_LENGTH_MESSAGE.formatted(type, min, max));
        }
    }

    public static void validateArgumentsCount(List<String> list, int expectedNumberOfParameters) {
        if (list.size() < expectedNumberOfParameters) {
            throw new IllegalArgumentException(
                    String.format(INVALID_NUMBER_OF_ARGUMENTS, expectedNumberOfParameters, list.size())
            );
        }
    }
}
