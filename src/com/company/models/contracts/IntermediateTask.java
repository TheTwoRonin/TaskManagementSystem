package com.company.models.contracts;

import com.company.models.enums.Priority;

public interface IntermediateTask extends Task {

    User getAssignee();

    void assignAssignee(User assignee);

    void unassignAssignee();

    Priority getPriority();

    void changePriority(Priority priority);
}
