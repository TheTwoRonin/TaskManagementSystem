package com.company.commands.operations.modification;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Bug;
import com.company.models.contracts.Task;
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

        Task task = taskManagementSystemRepository.findTaskById(id);
        if (!(task instanceof Bug))
            throw new IllegalArgumentException(String.format(CommandConstants.TASK_PRIORITY_ERR, id));
        ((Bug) task).changeSeverity(severity);
        return String.format(CommandConstants.ENUM_CHANGED_MESSAGE, CommandConstants.SEVERITY, id);
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_INPUT_MESSAGE);
        severity = ParsingHelpers.tryParseEnum(parameters.get(1), Severity.class);
    }
}