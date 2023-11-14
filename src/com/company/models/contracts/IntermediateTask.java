package com.company.models.contracts;

import com.company.models.enums.Priority;

public interface IntermediateTask extends Task {

    User getAssignee();

    Priority getPriority();

    void changePriority(Priority priority);
}
