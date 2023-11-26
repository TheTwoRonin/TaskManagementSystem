package com.company.commands.operations.modification;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Team;
import com.company.models.contracts.User;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandAndActivityConstants.ITEM_USER_ADDED_TO_TEAM;
import static com.company.commands.constants.CommandAndActivityConstants.USER_ADDED_TO_TEAM_MESSAGE;

public class AddUserToTeamCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private User user;

    private Team team;

    public AddUserToTeamCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }


    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        team.addMember(user);
        String description = ITEM_USER_ADDED_TO_TEAM.formatted(user.getName(), team.getName());
        user.addActivity(description);


        return USER_ADDED_TO_TEAM_MESSAGE.formatted(user.getName(), team.getName());
    }

    private void parseParameters(List<String> parameters) {
        user = taskManagementSystemRepository.findUserByName(parameters.get(0));
        team = taskManagementSystemRepository.findTeamByName(parameters.get(1));
    }
}
