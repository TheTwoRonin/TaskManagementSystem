package com.company.models;

import com.company.models.contracts.Activity;
import com.company.models.contracts.Comment;
import com.company.models.contracts.Task;
import com.company.models.enums.Status;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTask implements Task {

    private static final int[] TITLE_MIN_MAX_LENGTH = {10, 100};
    private static final String TITLE = "Title";
    private static final int[] DESCRIPTION_MIN_MAX_LENGTH = {10, 500};
    private static final String DESCRIPTION = "Description";
    public static final String CANNOT_ADVANCE_STATUS_ERR = "Cannot advance status, already at Done.";
    private final String CANNOT_REVERT_STATUS_ERR = "Cannot revert status, already at %s.";

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
    public void advanceStatus() {
        if (status.equals(Status.DONE)) {
            throw new IllegalArgumentException(CANNOT_ADVANCE_STATUS_ERR);
        }
        switch (status) {
            case NEW -> status = Status.UNSCHEDULED;
            case UNSCHEDULED -> status = Status.SCHEDULED;
            case NOT_DONE -> status = Status.IN_PROGRESS;
            case SCHEDULED, ACTIVE, IN_PROGRESS -> status = Status.DONE;
        }
    }

    @Override
    public void revertStatus() {
        if (status.equals(Status.ACTIVE) || status.equals(Status.NEW) || status.equals(Status.NOT_DONE))
            throw new IllegalArgumentException(String.format(CANNOT_REVERT_STATUS_ERR, status));
        switch (this.getClass().getSimpleName()) {
            case "BugImpl" -> status = Status.ACTIVE;
            case "StoryImpl" -> {
                if (status == Status.DONE) {
                    status = Status.IN_PROGRESS;
                } else {
                    status = Status.NOT_DONE;
                }
            }
            case "FeedbackImpl" -> {
                switch (status) {
                    case DONE -> status = Status.SCHEDULED;
                    case SCHEDULED -> status = Status.UNSCHEDULED;
                    default -> status = Status.NEW;
                }
            }
        }

    }
}
