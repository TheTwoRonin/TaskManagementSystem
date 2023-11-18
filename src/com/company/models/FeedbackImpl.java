package com.company.models;

import com.company.models.contracts.Feedback;
import com.company.models.enums.Status;

public class FeedbackImpl extends BaseTask implements Feedback {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be New, Unscheduled, Scheduled, or Done.";

    private int rating;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description, Status.NEW);
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
