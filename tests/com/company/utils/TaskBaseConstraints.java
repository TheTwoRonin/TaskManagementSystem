package com.company.utils;

import com.company.models.CommentImpl;
import com.company.models.contracts.Comment;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;

import java.util.List;

public class TaskBaseConstraints {

    public static final int TITLE_LEN_MIN = 10;
    public static final int DESCRIPTION_LEN_MIN = 10;

    public static final String VALID_TITLE = TestUtilities.getString(TITLE_LEN_MIN + 1);
    public static final String VALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_LEN_MIN + 1);
    public static final Priority VALID_PRIORITY = Priority.HIGH;
    public static final Severity VALID_SEVERITY = Severity.CRITICAL;
    public static final List<String> VALID_STEPS = TestUtilities.getList(3);
    public static final Comment VALID_COMMENT = new CommentImpl(TestUtilities.getString(4), TestUtilities.getString(4));

    public static final String INVALID_TITLE = TestUtilities.getString(TITLE_LEN_MIN - 1);
    public static final String INVALID_DESCRIPTION = TestUtilities.getString(DESCRIPTION_LEN_MIN - 1);


}
