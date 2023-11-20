package com.company.models;

import com.company.models.contracts.Bug;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Status;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends BaseIntermediateTask implements Bug {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be Active or Done";

    private Severity severity;
    private final List<String> steps;

    public BugImpl(int id, String title, String description,
                   User assignee, Priority priority, Severity severity, List<String> steps) {
        super(id, title, description, Status.ACTIVE, assignee, priority);
        this.severity = severity;
        this.steps = new ArrayList<>(steps);

    }

    @Override
    public List<String> getSteps() {
        return new ArrayList<>(steps);
    }

    @Override
    public Severity getSeverity() {
        return severity;
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
// TODO: 20.11.2023 г. setters?
// TODO: 20.11.2023 г. split commands to packages