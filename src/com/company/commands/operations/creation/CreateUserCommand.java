package com.company.commands.operations.creation;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.User;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandConstants.USER;

public class CreateUserCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private String name;
    public CreateUserCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        taskManagementSystemRepository.userIsUnique(name);

        User createdUser = taskManagementSystemRepository.createUser(name);

        return String.format(CommandConstants.CREATED_W_NAME_MESSAGE, USER, createdUser.getName());
    }

    private void parseParameters(List<String> parameters) {
        name = parameters.get(0);
    }

}