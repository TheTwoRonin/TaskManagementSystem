package com.company.models.idd;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.models.Activity;
import com.company.models.contracts.Feedback;
import com.company.models.enums.Status;
import com.company.models.idd.base.BaseTask;

import static com.company.commands.constants.CommandAndActivityConstants.*;

public class FeedbackImpl extends BaseTask implements Feedback {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be New, Unscheduled, Scheduled, or Done.";

    private int rating;

    public FeedbackImpl(int id, String title, String description, int rating) {
        super(id, title, description, Status.NEW);
        this.rating = rating;
        addActivity(new Activity(CommandAndActivityConstants.ITEM_WITH_ID_CREATION
                .formatted(FEEDBACK, getId())));
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public void changeStatus(Status status) {
        Status old_status = getStatus();
        if (!status.equals(Status.NEW) && !status.equals(Status.UNSCHEDULED) &&
                !status.equals(Status.SCHEDULED) && !status.equals(Status.DONE))
            throw new IllegalArgumentException(INVALID_STATUS_ERR);
        super.changeStatus(status);
        addActivity(new Activity(CommandAndActivityConstants.ITEM_WITH_ID_MODIFICATION
                .formatted(FEEDBACK, getId(), STATUS, old_status, getStatus())));
    }

    @Override
    public void changeRating(int rating) {
        int old_rating = getRating();
        this.rating = rating;
        addActivity(new Activity(CommandAndActivityConstants.ITEM_WITH_ID_MODIFICATION
                .formatted(FEEDBACK, getId(), RATING, Integer.toString(old_rating), Integer.toString(getRating()))));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName().replaceAll("Impl", "") + " " + super.toString() + "Rating: " + getRating() + "\n";
    }
}
