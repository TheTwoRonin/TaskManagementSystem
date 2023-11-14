package com.company.models;

import com.company.models.contracts.Activity;
import com.company.models.contracts.Comment;
import com.company.models.contracts.Task;
import com.company.models.enums.Priority;
import com.company.models.enums.Status;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTask implements Task {

    private static final int[] TITLE_MIN_MAX_LENGTH = {10, 100};
    private static final String TITLE = "Title";
    private static final int[] DESCRIPTION_MIN_MAX_LENGTH = {10, 500};
    private static final String DESCRIPTION = "Description";
//    public static final String CANNOT_ADVANCE_STATUS_ERR = "Cannot advance status, already at Done.";
//    private final String CANNOT_REVERT_STATUS_ERR = "Cannot revert status, already at %s.";

    private final int id;
    private String title;
    private String description;
    private Status status;
    private final List<Comment> comments;
    private final List<Activity> changes;


    public BaseTask(int id, String title, String description, Status status) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.status = status;
        comments = new ArrayList<>();
        changes = new ArrayList<>();
    }

    private void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_MAX_LENGTH[0],
                TITLE_MIN_MAX_LENGTH[1], TITLE);
        this.title = title;
    }

    private void setDescription(String description) {
        ValidationHelpers.validateStringLength(description, DESCRIPTION_MIN_MAX_LENGTH[0],
                DESCRIPTION_MIN_MAX_LENGTH[1], DESCRIPTION);
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
    public List<Activity> getChanges() {
        return new ArrayList<>(changes);
    }

    @Override
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void removeComment(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public void changeStatus(Status status) {
        this.status = status;
    }
}
