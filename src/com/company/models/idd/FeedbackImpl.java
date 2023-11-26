package com.company.models.idd;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.models.contracts.Feedback;
import com.company.models.enums.Status;
import com.company.models.idd.base.BaseTask;

import static com.company.commands.constants.CommandAndActivityConstants.FEEDBACK;
import static com.company.commands.constants.CommandAndActivityConstants.RATING;

public class FeedbackImpl extends BaseTask implements Feedback {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be New, Unscheduled, Scheduled, or Done.";
    private static final String TO_STRING = "%s %s%nRating: %s%n";

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
        int old_rating = getRating();
        this.rating = rating;
        addActivity(CommandAndActivityConstants.ITEM_WITH_ID_MODIFICATION
                .formatted(getClassName(), getId(), RATING, Integer.toString(old_rating), Integer.toString(getRating())));
    }

    @Override
    protected String getClassName() {
        return FEEDBACK;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING, getClassName(), super.toString(), getRating());
    }
}
