package com.company.commands.constants;

public class ActivityConstants {
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
}
