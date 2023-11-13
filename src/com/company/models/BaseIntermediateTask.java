package com.company.models;

import com.company.models.contracts.IntermediateTask;
import com.company.models.enums.Priority;
import com.company.models.enums.Status;

// TODO: 13.11.2023 г. fix stupid name
public class BaseIntermediateTask extends BaseTask implements IntermediateTask {
// TODO: 13.11.2023 г.
//    private Member assignee;

    private Priority priority;

    public BaseIntermediateTask(int id, String title, String description, Status status, Priority priority) {
        super(id, title, description, status);
//        this.assignee = assignee;
        this.priority = priority;
    }


//    @Override
//    public Member getAssignee() {
//        return assignee;
//    }

    @Override
    public Priority getPriority() {
        return priority;
    }
}
