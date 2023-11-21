package com.company.models.idd.base;

import com.company.models.contracts.Comment;
import com.company.models.contracts.Log;
import com.company.models.contracts.Task;
import com.company.models.enums.Status;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseTask implements Task {

    private static final int[] TITLE_MIN_MAX_LENGTH = {10, 100};
    private static final String TITLE = "Title";
    private static final int[] DESCRIPTION_MIN_MAX_LENGTH = {10, 500};
    private static final String DESCRIPTION = "Description";

    private final int id;
    private String title;
    private String description;
    private Status status;
    private final List<Comment> comments;
    private final List<Log> activityHistory;


    public BaseTask(int id, String title, String description, Status status) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.status = status;
        this.comments = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
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
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    @Override
    public void changeStatus(Status status) {
        this.status = status;
    }

    @Override
    public void addActivity(Log activity) {
        this.activityHistory.add(activity);
    }

    @Override
    public List<Log> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public String toString() {
        return String.format(""" 
                        #ID- %d
                        Title: %s
                        Description: "%s"
                        Status: %s
                        Comments:
                        %s
                        """
                , getId(), getTitle(), getDescription(), getStatus(), ParsingHelpers.tryParseList(getComments()));
    }
}
