package com.company.models.contracts;

import com.company.models.enums.Priority;

public interface IntermediateTask extends Task {
    //        Member getAssignee();todo
    Priority getPriority();
}
