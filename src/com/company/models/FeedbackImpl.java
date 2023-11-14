package com.company.models;

import com.company.models.contracts.Feedback;
import com.company.models.enums.Status;

public class FeedbackImpl extends BaseTask implements Feedback {

    private int rating;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description, Status.NEW);
    }

    @Override
    public int getRating() {
        return rating;
    }
}
