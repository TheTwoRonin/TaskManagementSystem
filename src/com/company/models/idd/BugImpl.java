package com.company.models.idd;

import com.company.models.Activity;
import com.company.models.contracts.Bug;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Status;
import com.company.models.idd.base.BaseIntermediateTask;
import com.company.utils.ParsingHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.company.commands.constants.ActivityConstants.*;

public class BugImpl extends BaseIntermediateTask implements Bug {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be Active or Done";

    private Severity severity;
    private final List<String> steps;

    public BugImpl(int id, String title, String description,
                   User assignee, Priority priority, Severity severity, List<String> steps) {
        super(id, title, description, Status.ACTIVE, assignee, priority);
        this.severity = severity;
        this.steps = new ArrayList<>(steps);
        //TODO integrate in IntermediateTask
        addActivity(new Activity(ITEM_WITH_ID_CREATION
                .formatted(BUG, getId())));
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
        Status old_status = getStatus();
        if (!status.equals(Status.ACTIVE) && !status.equals(Status.DONE))
            throw new IllegalArgumentException(INVALID_STATUS_ERR);
        super.changeStatus(status);
        addActivity(new Activity(ITEM_WITH_ID_MODIFICATION
                .formatted(BUG, getId(), STATUS, old_status, getStatus())));
    }

    @Override
    public void changeSeverity(Severity severity) {
        Severity old_severity = getSeverity();
        this.severity = severity;
        addActivity(new Activity(ITEM_WITH_ID_MODIFICATION
                .formatted(BUG, getId(), SEVERITY, old_severity, getSeverity())));
    }

    @Override
    public void changePriority(Priority priority) {
        Priority old_priority = getPriority();
        super.changePriority(priority);
        addActivity(new Activity(ITEM_WITH_ID_MODIFICATION
                .formatted(BUG, getId(), PRIORITY, old_priority, getPriority())));
    }

    @Override
    public void unassignAssignee() {
        User assignee = getAssignee();
        super.unassignAssignee();
        addActivity(new Activity(ITEM_WITH_ID_UNASSIGNED_FROM_USER
                .formatted(this.getClass().getInterfaces()[0], getId(), assignee.getName())));
    }

    @Override
    public String toString() {
        String sb = getClass().getSimpleName().replaceAll("Impl", "") + " " + super.toString() + "Severity: " + getSeverity() + "\n" +
                ParsingHelpers.tryParseList(getSteps()) + "\n";
        return sb;
    }
}
// TODO: 20.11.2023 г. setters?