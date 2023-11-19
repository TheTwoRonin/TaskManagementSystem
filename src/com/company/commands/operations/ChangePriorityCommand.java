package com.company.commands.operations;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.IntermediateTask;
import com.company.models.contracts.Task;
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

        Task task = taskManagementSystemRepository.findTaskById(id);
        if (!(task instanceof IntermediateTask))
            throw new IllegalArgumentException(String.format(CommandConstants.TASK_PRIORITY_ERR, id));
        ((IntermediateTask) task).changePriority(priority);
        return String.format(CommandConstants.PRIORITY_CHANGED_MESSAGE, id);
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_INPUT_MESSAGE);
        priority = ParsingHelpers.tryParseEnum(parameters.get(1), Priority.class);
    }
}