package com.company.models.idd;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Size;
import com.company.models.enums.Status;
import com.company.models.idd.base.BaseTaskAssignment;

import static com.company.commands.constants.CommandAndActivityConstants.SIZE;
import static com.company.commands.constants.CommandAndActivityConstants.STORY;

public class StoryImpl extends BaseTaskAssignment implements Story {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be Not Done, InProgress, or Done";
    private static final String TO_STRING = "%s %s%nSize: %s%n";

    private Size size;

    public StoryImpl(int id, String title, String description,
                     User assignee, Priority priority, Size size) {
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

    @Override
    public void changeSize(Size size) {
        Size old_size = getSize();
        this.size = size;
        addActivity(CommandAndActivityConstants.ITEM_WITH_ID_MODIFICATION
                .formatted(getClassName(), getId(), SIZE, old_size, getSize()));
    }

    @Override
    protected String getClassName() {
        return STORY;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING, getClassName(), super.toString(), getSize());


    }
}
