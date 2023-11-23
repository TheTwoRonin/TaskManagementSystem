package com.company.commands.operations.modification;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.TaskAssignment;
import com.company.models.contracts.User;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandConstants.INVALID_INPUT_MESSAGE;
import static com.company.commands.constants.CommandConstants.TASK_ASSIGNED_TO_USER_MESSAGE;

public class AssignTaskToUserCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    //TODO should it be intermediate or Task?
    private TaskAssignment task;

    private User user;


    public AssignTaskToUserCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        user.assignTask(task);
        task.assignAssignee(user);
        return TASK_ASSIGNED_TO_USER_MESSAGE.formatted(task.getId(), user.getName());
    }

    private void parseParameters(List<String> parameters) {
        int taskId = ParsingHelpers.tryParseInt(parameters.get(0), INVALID_INPUT_MESSAGE);
        task = taskManagementSystemRepository.findTaskAssignmentById(taskId);
        user = taskManagementSystemRepository.findUserByName(parameters.get(1));
    }
}
