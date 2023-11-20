package com.company.utils;

public class NamingConstraints {

    public static final int[] NAME_LEN_MIN_MAX = {5,15};

    public static final int[] BOARD_NAME_LEN_MIN_MAX = {5,10};

    public static final String VALID_NAME = TestUtilities.getString(NAME_LEN_MIN_MAX[0] + 1);

    public static final String VALID_NAME2 = TestUtilities.getString(NAME_LEN_MIN_MAX[0] + 2);
    public static final String VALID_BOARD_NAME = TestUtilities.getString(BOARD_NAME_LEN_MIN_MAX[0] + 1);

    public static final String INVALID_NAME_TOO_SHORT = TestUtilities.getString(NAME_LEN_MIN_MAX[0] - 1);

    public static final String INVALID_NAME_TOO_LONG = TestUtilities.getString(NAME_LEN_MIN_MAX[1] + 1);
}
