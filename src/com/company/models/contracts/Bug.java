package com.company.models.contracts;

import com.company.models.enums.Severity;

import java.util.List;

public interface Bug extends IntermediateTask {

    List<String> getSteps();

    Severity getSeverity();

    void addStep(String step);
}
