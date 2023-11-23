package com.company.commands.constants;

public class CommandAndActivityConstants {

    // Types - used in placeholder
    public static final String BUG = "Bug";
    public static final String FEEDBACK = "Feedback";
    public static final String STORY = "Story";
    public static final String USER = "User";
    public static final String TEAM = "Team";
    public static final String BOARD = "Board";

    //Fields - used in placeholders
    public static final String PRIORITY = "Priority";
    public static final String STATUS = "Status";
    public static final String SEVERITY = "Severity";
    public static final String RATING = "Rating";
    public static final String SIZE = "Size";
    public static final String CREATED_W_NAME_MESSAGE = "%s with name \033[3m%s\033[0m was created.";
    public static final String TASK_CREATED_MESSAGE = "%s with ID \033[3m%d\033[0m was created.";
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    public static final String ITEM_WITH_NAME_NOT_FOUND_ERR = "No %s with name \033[3m%s\033[0m found.";
    public static final String DUPLICATE_FOUND_ERR = "%s with this name has been already created.";
    public static final String TASK_NOT_FOUND_ERR = "No task with id \033[3m%d\033[0m found.";
    public final static String COMMENT_ADDED_MESSAGE = "\033[3m%s\033[0m added comment successfully.";
    public final static String USER_ADDED_TO_TEAM_MESSAGE = "User \033[3m%s\033[0m added to team \033[3m%s\033[0m successfully.";
    public final static String TASK_ASSIGNED_TO_USER_MESSAGE = "Task with ID \033[3m%d\033[0m added to user \033[3m%s\033[0m successfully.";
    public final static String TASK_UNASSIGNED_FROM_USER_MESSAGE = "Task with ID \033[3m%d\033[0m had its asignee removed successfully.";
    public static final String TASK_PRIORITY_ERR = "Task with ID \033[3m%d\033[0m does not have priority attribute!";
    public static final String ENUM_CHANGED_MESSAGE = "%s of task with ID \033[3m%d\033[0m changed successfully.";

    //Creation// - used in models' constructors
    public static String ITEM_WITH_ID_CREATION = "%s created with ID #\033[3m%d\033[0m";
    public static String ITEM_WITH_NAME_CREATION = "%s \033[3m%s\033[0m created.";
    public static String ITEM_BOARD_CREATION = "Board \033[3m%s\033[0m created in team \033[3m%s\033[0m";

    //Modification// - used in models
    public static String ITEM_WITH_ID_MODIFICATION = "%s #\033[3m%d\033[0m %s changed from \033[3m%s\033[0m to \033[3m%s\033[0m";
    public static String ITEM_WITH_ID_COMMENT_ADDED = "%s #\033[3m%d\033[0m comment: ''%s'' added by %s ";

    //Add - used in commands
    public static String ITEM_USER_ADDED_TO_TEAM = "User \033[3m%s\033[0m added to team \033[3m%s\033[0m";
    //Assign - used in commands
    public static String ITEM_WITH_ID_ASSIGNED_TO_USER = "%s with ID #\033[3m%d\033[0m assigned to user \033[3m%s\033[0m";
    public static String ITEM_WITH_ID_UNASSIGNED_FROM_USER = "%s with ID #\033[3m%d\033[0m unassigned from user \033[3m%s\033[0m";
    public static String ITEM_WITH_ID_ADDED_TO_BOARD = "%s with ID #\033[3m%d\033[0m added to board \033[3m%s\033[0m";
    public static final String NEW_LINE = "\n------\n";
}
