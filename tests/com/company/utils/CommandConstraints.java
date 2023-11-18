package com.company.utils;

import com.company.models.UserImplTests;
import com.company.models.contracts.User;
import org.junit.jupiter.api.Test;

public class CommandConstraints {

    public static final String VALID_ASSIGNEE_STR = TestUtilities.getString(4);
    public static final String INVALID_ASSIGNEE_STR = TestUtilities.getString(5);
    public static final String VALID_PRIORITY_STR = "high";
    public static final String VALID_SEVERITY_STR = "critical";
    public static final String VALID_STEPS = TestUtilities.getString(5);
    public static final String VALID_RATING_STR = "1";
    public static final String INVALID_RATING_STR = TestUtilities.getString(5);
    public static final String INVALID_ENUM = "INVALID_ENUM";
}
