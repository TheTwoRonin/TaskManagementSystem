package com.company.models;

import com.company.models.contracts.Bug;
import com.company.models.contracts.Comment;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends BaseIntermediateTask implements Bug {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be Active or Done";

    private List<String> steps;
    private Severity severity;

    public BugImpl(int id, String title, String description, List<Comment> comments,
                   User assignee, Priority priority, Severity severity) {
        super(id, title, description, Status.ACTIVE, comments, assignee, priority);
        steps = new ArrayList<>();
        this.severity = severity;
    }

    @Override
    public List<String> getSteps() {
        return steps;
    }

    @Override
    public Severity getSeverity() {
        return severity;
    }

    @Override
    public void addStep(String step) {
        steps.add(step);
    }

    @Override
    public void changeStatus(Status status) {
        if (!status.equals(Status.ACTIVE) && !status.equals(Status.DONE))
            throw new IllegalArgumentException(INVALID_STATUS_ERR);
        super.changeStatus(status);
    }

    @Override
    public void changeSeverity(Severity severity) {
        this.severity = severity;
    }
}
