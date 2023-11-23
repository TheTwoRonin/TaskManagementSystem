package com.company.models.idd.base;

import com.company.models.contracts.TaskAssignment;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Status;

public abstract class BaseTaskAssignment extends BaseTask implements TaskAssignment {

    private static final String ASSIGNEE_ASSIGNED_ERR = "%s already assigned to task.";
    private static final String NO_ASSIGNEE_ASSIGNED_ERR = "No assignee assigned!";

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
        checkifAssigneeAssigned();
        assignee = null;
    }

    @Override
    public User getAssignee() {
        checkifAssigneeAssigned();
        return assignee;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public void changePriority(Priority priority) {
        this.priority = priority;
    }

    private void checkifAssigneeAssigned() {
        if (assignee == null)
            throw new IllegalArgumentException(NO_ASSIGNEE_ASSIGNED_ERR);
    }

    @Override
    public String toString() {
        String sb = super.toString() + """
                Assignee: %s
                Priority: %s
                """.formatted(getAssignee().getName(), getPriority());
        return sb;
    }
}
