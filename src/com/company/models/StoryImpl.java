package com.company.models;

import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Size;
import com.company.models.enums.Status;

public class StoryImpl extends BaseIntermediateTask implements Story {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be Not Done, InProgress, or Done";

    private Size size;

    public StoryImpl(int id, String title, String description, User assignee, Priority priority, Size size) {
        super(id, title, description, Status.NOT_DONE, assignee, priority);
        this.size = size;
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void changeStatus(Status status) {
        if (!status.equals(Status.NOT_DONE) && !status.equals(Status.IN_PROGRESS) && !status.equals(Status.DONE))
            throw new IllegalArgumentException(INVALID_STATUS_ERR);
        super.changeStatus(status);
    }
}
