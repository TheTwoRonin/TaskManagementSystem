package com.company.models;

import com.company.models.enums.Severity;

import java.util.List;

public class BugImpl /*extends BaseIntermediateTask implements Bug */ {

    private List<String> steps;
    private Severity severity;

//    @Override
    public List<String> getSteps() {
        return steps;
    }

//    @Override
    public Severity getSeverity() {
        return severity;
    }
}
