package com.company.utils;

import java.util.List;

public class TestUtilities {

    public static String getString(int length) {
        return "x".repeat(length);
    }

    public static List<String> getList(int size) {
        return List.of(getString(size).repeat(size));
    }

}
