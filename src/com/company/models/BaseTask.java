package com.company.models;

import com.company.models.contracts.Comment;
import com.company.models.contracts.Task;
import com.company.models.enums.Status;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BaseTask implements Task {

    private static final int TITLE_MIN_LENGTH = 10;
    private static final int TITLE_MAX_LENGTH = 100;
    private static final String TITLE = "Title";
    private static final int DESCRIPTION_MIN_LENGTH = 10;
    private static final int DESCRIPTION_MAX_LENGTH = 105;
    private static final String DESCRIPTION = "Description";
    private static final String INCORRECT_LENGTH_MESSAGE = "%s should be between %d and %d symbols";

    private final int id;
    private String title;
    private String description;
    private Status status;
    private final List<Comment> comments;
    // TODO: 13.11.2023 г. changes after creating ActivityHistory
//    private final List<ActivityHistory> changes;


    public BaseTask(int id, String title, String description, Status status) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.status = status;
        comments = new ArrayList<>();
//        changes = new ArrayList<>();
    }


    private void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH,
                String.format(INCORRECT_LENGTH_MESSAGE, TITLE, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH));
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH,
                String.format(INCORRECT_LENGTH_MESSAGE, DESCRIPTION, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH));
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    @Override
    public List<String> getChanges() {
//        return new ArrayList<>(changes);
        // TODO: 13.11.2023 г.  
        return null;
    }

}
