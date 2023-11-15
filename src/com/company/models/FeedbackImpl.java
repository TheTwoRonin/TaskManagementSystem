package com.company.models;

import com.company.models.contracts.Comment;
import com.company.models.contracts.Feedback;
import com.company.models.enums.Status;

import java.util.List;

public class FeedbackImpl extends BaseTask implements Feedback {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be New, Unscheduled, Scheduled, or Done.";

    private int rating;

    public FeedbackImpl(int id, String title, String description, List<Comment> comments, int rating) {
        super(id, title, description, Status.NEW, comments);
        this.rating = rating;
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void changeStatus(Status status) {
        if (!status.equals(Status.NEW) && !status.equals(Status.UNSCHEDULED) &&
                !status.equals(Status.SCHEDULED) && !status.equals(Status.DONE))
            throw new IllegalArgumentException(INVALID_STATUS_ERR);
        super.changeStatus(status);
    }

    @Override
    public void changeRating(int rating) {
        this.rating = rating;
    }
}
