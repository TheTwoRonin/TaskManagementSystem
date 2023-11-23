package com.company.commands.operations.modification;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.TaskAssignment;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandConstants.INVALID_INPUT_MESSAGE;
import static com.company.commands.constants.CommandConstants.TASK_UNASSIGNED_FROM_USER_MESSAGE;

public class UnassignTaskFromUserCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private TaskAssignment task;

    public UnassignTaskFromUserCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        task.unassignAssignee();
        return TASK_UNASSIGNED_FROM_USER_MESSAGE.formatted(task.getId());
    }

    private void parseParameters(List<String> parameters) {
        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_INPUT_MESSAGE);
        task = taskManagementSystemRepository.findTaskAssignmentById(taskId);
    }
}
