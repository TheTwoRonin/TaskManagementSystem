package com.company.utils;

public class TaskConstraints {

    public static final int TITLE_LEN_MIN = 10;
    public static final int DESCRIPTION_LEN_MIN = 10;

    public static final String VALID_TITLE = TestUtilities.getString(TITLE_LEN_MIN + 1);
    public static final String VALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_LEN_MIN + 1);
    public static final String INVALID_TITLE = TestUtilities.getString(TITLE_LEN_MIN - 1);
    public static final String INVALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_LEN_MIN - 1);


}
