package com.company.models.idd.base;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.models.contracts.TaskAssignment;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Status;

import static com.company.commands.constants.CommandAndActivityConstants.PRIORITY;

public abstract class BaseTaskAssignment extends BaseTask implements TaskAssignment {

    private static final String ASSIGNEE_ASSIGNED_ERR = "%s already assigned to task.";
    private static final String NO_ASSIGNEE_ASSIGNED_ERR = "No assignee assigned!";
    private static final String TO_STRING = "%s%nAssignee: %s%nPriority: %s";

    private User assignee;
    private Priority priority;

    public BaseTaskAssignment(int id, String title, String description, Status status,
                              User assignee, Priority priority) {
        super(id, title, description, status);
        this.assignee = assignee;
        this.priority = priority;
    }

    public void assignAssignee(User assignee) {
        if (this.assignee.equals(assignee)) {
            throw new IllegalArgumentException(String.format(ASSIGNEE_ASSIGNED_ERR, assignee.getName()));
        }
        this.assignee = assignee;

    }

    public void unassignAssignee() {
        checkIfAssigneeAssigned();
        User old_assignee = getAssignee();
        assignee = null;
        addActivity(CommandAndActivityConstants.ITEM_WITH_ID_UNASSIGNED_FROM_USER
                .formatted(getClassName(), getId(), old_assignee.getName()));
    }

    @Override
    public User getAssignee() {
        checkIfAssigneeAssigned();
        return assignee;
    }

    private void checkIfAssigneeAssigned() {
        if (assignee == null)
            throw new IllegalArgumentException(NO_ASSIGNEE_ASSIGNED_ERR);
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void changePriority(Priority priority) {
        Priority old_priority = getPriority();
        this.priority = priority;
        addActivity(CommandAndActivityConstants.ITEM_WITH_ID_MODIFICATION
                .formatted(getClassName(),
                        getId(),
                        PRIORITY,
                        old_priority,
                        getPriority()));
    }

    @Override
    public String toString() {
        return TO_STRING.formatted(super.toString(), getAssignee().getName(), getPriority());
    }
}
