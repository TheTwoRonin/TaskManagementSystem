package com.company.commands.operations;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Team;
import com.company.models.contracts.User;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandConstants.TEAM;
import static com.company.commands.constants.CommandConstants.USER;

public class CreateTeamCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private String name;
    public CreateTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        taskManagementSystemRepository.teamIsUnique(name);

        Team createdTeam = taskManagementSystemRepository.createTeam(name);

        return String.format(CommandConstants.CREATED_W_NAME_MESSAGE, TEAM, createdTeam.getName());
    }

    private void parseParameters(List<String> parameters) {
        name = parameters.get(0);
    }

}
