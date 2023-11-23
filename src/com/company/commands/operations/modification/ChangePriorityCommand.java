package com.company.commands.operations.modification;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.TaskAssignment;
import com.company.models.enums.Priority;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;


public class ChangePriorityCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private int id;
    private Priority priority;

    public ChangePriorityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        TaskAssignment task = taskManagementSystemRepository.findTaskAssignmentById(id);

        task.changePriority(priority);
        return String.format(CommandConstants.ENUM_CHANGED_MESSAGE, CommandConstants.PRIORITY, id);
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_INPUT_MESSAGE);
        priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class);
    }
}