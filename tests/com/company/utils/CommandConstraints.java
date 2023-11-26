package com.company.utils;

import java.util.List;

public class CommandConstraints {

    public static final String VALID_ASSIGNEE_STR = TestUtilities.getString(5);
    public static final String INVALID_ASSIGNEE_STR = TestUtilities.getString(6);
    public static final String VALID_PRIORITY_STR = "low";
    public static final String VALID_SEVERITY_STR = "minor";
    public static final String VALID_STATUS_STR = "done";
    public static final String VALID_SIZE_STR = "small";
    public static final String VALID_STEPS = TestUtilities.getString(5);
    public static final String VALID_NUM_STR = "1";
    public static final String INVALID_NUM_STR = TestUtilities.getString(5);
    public static final String INVALID_ENUM = TestUtilities.getString(5);
    public static final String VALID_CONTENT = TestUtilities.getString(5);
    public static final String VALID_AUTHOR = TestUtilities.getString(5);
    public static final String INVALID_DESCRIPTION_STR = TestUtilities.getString(0);
    public static final String VALID_BUG_STATUS_STR = "ACTIVE";
    public static final String VALID_FEEDBACK_STATUS_STR = "NEW";
    public static final String VALID_STORY_STATUS_STR = "NOT DONE";
    public static final String TITLE_STRING = "title";
    public static final String DESCRIPTION_STRING = "description";
    public static final String ASSIGNEE_STRING = "assignee";
    public static final String STATUS_STRING = "status";
    public static final String PRIORITY_STRING = "priority";
    public static final String SEVERITY_STRING = "severity";
    public static final List<String> BUG_FILTER_LIST = List.of(TITLE_STRING, DESCRIPTION_STRING, ASSIGNEE_STRING, STATUS_STRING, PRIORITY_STRING, SEVERITY_STRING);
    public static final String SIZE_STRING = "size";
    public static final List<String> STORY_FILTER_LIST = List.of(TITLE_STRING, DESCRIPTION_STRING, ASSIGNEE_STRING, STATUS_STRING, PRIORITY_STRING, SIZE_STRING);
    public static final String RATING_STRING = "rating";
    public static final List<String> FEEDBACK_FILTER_LIST = List.of(TITLE_STRING, DESCRIPTION_STRING, RATING_STRING, STATUS_STRING);
}
