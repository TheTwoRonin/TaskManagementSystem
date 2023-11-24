package com.company.models.enums;

public enum Status {
    NEW,
    ACTIVE,
    UNSCHEDULED,
    SCHEDULED,
    FIXED,
    NOT_DONE,
    IN_PROGRESS,
    DONE;

    @Override
    public String toString() {
        switch (this) {
            case NEW:
                return "New";
            case ACTIVE:
                return "Active";
            case UNSCHEDULED:
                return "Unscheduled";
            case SCHEDULED:
                return "Scheduled";
            case FIXED:
                return "Fixed";
            case NOT_DONE:
                return "Not Done";
            case IN_PROGRESS:
                return "In Progress";
            case DONE:
                return "Done";
            default:
                return null;
        }
    }

}
