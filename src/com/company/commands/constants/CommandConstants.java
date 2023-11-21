package com.company.commands.constants;

public class CommandConstants {

    public static final String BUG = "Bug";
    public static final String FEEDBACK = "Feedback";
    public static final String STORY = "Story";
    public static final String USER = "User";
    public static final String TEAM = "Team";
    public static final String BOARD = "Board";
    public static final String PRIORITY = "Priority";
    public static final String STATUS = "Status";
    public static final String SEVERITY = "Severity";
    public static final String CREATED_W_NAME_MESSAGE = "%s with name \033[3m%s\033[0m was created.";
    public static final String TASK_CREATED_MESSAGE = "%s with ID \033[3m%d\033[0m was created.";
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number.";
    public static final String USER_NOT_FOUND_ERR = "No user with name \033[3m%s\033[0m found.";
    public static final String TEAM_NOT_FOUND_ERR = "No team with name \033[3m%s\033[0m found.";
    public static final String BOARD_NOT_FOUND_ERR = "No board with name \033[3m%s\033[0m found.";
    public static final String TASK_NOT_FOUND_ERR = "No task with id \033[3m%d\033[0m found.";
    public final static String COMMENT_ADDED_MESSAGE = "\033[3m%s\033[0m added comment successfully.";
    public final static String USER_ADDED_TO_TEAM_MESSAGE = "User \033[3m%s\033[0m added to team \033[3m%s\033[0m successfully.";
    public final static String TASK_ASSIGNED_TO_USER_MESSAGE = "Task with ID \033[3m%d\033[0m added to user \033[3m%s\033[0m successfully.";
    public final static String TASK_UNASSIGNED_FROM_USER_MESSAGE = "Task with ID \033[3m%d\033[0m had its asignee removed successfully.";
    public static final String TASK_PRIORITY_ERR = "Task with ID \033[3m%d\033[0m does not have priority attribute!";
    public static final String ENUM_CHANGED_MESSAGE = "%s of task with ID \033[3m%d\033[0m changed successfully.";

}
