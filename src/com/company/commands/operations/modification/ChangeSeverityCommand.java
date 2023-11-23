package com.company.commands.operations.modification;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Bug;
import com.company.models.enums.Severity;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

public class ChangeSeverityCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private int id;
    private Severity severity;

    public ChangeSeverityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Bug bug = taskManagementSystemRepository.findBugById(id);

        bug.changeSeverity(severity);
        return String.format(CommandAndActivityConstants.ENUM_CHANGED_MESSAGE, CommandAndActivityConstants.SEVERITY, id);
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInt(parameters.get(0), CommandAndActivityConstants.INVALID_INPUT_MESSAGE);
        severity = ParsingHelpers.tryParseEnum(parameters.get(1), Severity.class);
    }
}