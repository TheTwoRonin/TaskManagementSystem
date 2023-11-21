package com.company.models.idd;

import com.company.models.Activity;
import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Size;
import com.company.models.enums.Status;
import com.company.models.idd.base.BaseIntermediateTask;

import static com.company.commands.constants.ActivityConstants.*;

public class StoryImpl extends BaseIntermediateTask implements Story {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be Not Done, InProgress, or Done";

    private Size size;

    public StoryImpl(int id, String title, String description,
                     User assignee, Priority priority, Size size) {
        super(id, title, description, Status.NOT_DONE, assignee, priority);
        this.size = size;
        addActivity(new Activity(ITEM_WITH_ID_CREATION
                .formatted(STORY, getId())));
    }

    @Override
    public Size getSize() {
        return size;
    }

    @Override
    public void changeStatus(Status status) {
        Status old_status = getStatus();
        if (!status.equals(Status.NOT_DONE) && !status.equals(Status.IN_PROGRESS) && !status.equals(Status.DONE))
            throw new IllegalArgumentException(INVALID_STATUS_ERR);
        super.changeStatus(status);
        addActivity(new Activity(ITEM_WITH_ID_MODIFICATION
                .formatted(STORY, getId(), STATUS, old_status, getStatus())));
    }

    @Override
    public void changeSize(Size size) {
        Size old_size = getSize();
        this.size = size;
        addActivity(new Activity(ITEM_WITH_ID_MODIFICATION
                .formatted(STORY, getId(), SIZE, old_size, getSize())));
    }

    @Override
    public void changePriority(Priority priority) {
        Priority old_priority = getPriority();
        super.changePriority(priority);
        addActivity(new Activity(ITEM_WITH_ID_MODIFICATION
                .formatted(STORY, getId(), PRIORITY, old_priority, getPriority())));
    }

    @Override
    public void unassignAssignee() {
        User assignee = getAssignee();
        super.unassignAssignee();
        //TODO Implement in IntermediateTask
        addActivity(new Activity(ITEM_WITH_ID_UNASSIGNED_FROM_USER
                .formatted(STORY, getId(), assignee.getName())));
    }
}
