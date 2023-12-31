package com.company.models.idd;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.models.contracts.Bug;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Status;
import com.company.models.idd.base.BaseTaskAssignment;
import com.company.utils.ListingHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.company.commands.constants.CommandAndActivityConstants.BUG;
import static com.company.commands.constants.CommandAndActivityConstants.SEVERITY;

public class BugImpl extends BaseTaskAssignment implements Bug {

    private static final String INVALID_STATUS_ERR = "Invalid status, can be Active or Done";
    private static final String TO_STRING = "%s %s%nSeverity: %s%n%s";

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
        Severity old_severity = getSeverity();
        this.severity = severity;
        addActivity(CommandAndActivityConstants.ITEM_WITH_ID_MODIFICATION
                .formatted(getClassName(), getId(), SEVERITY, old_severity, getSeverity()));
    }

    @Override
    protected String getClassName() {
        return BUG;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING, getClassName(), super.toString(), getSeverity(),
                ListingHelpers.parseList(getSteps()));
    }
}