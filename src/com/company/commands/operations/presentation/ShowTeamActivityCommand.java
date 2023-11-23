package com.company.commands.operations.presentation;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Team;
import com.company.models.contracts.User;
import com.company.utils.ListingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ShowTeamActivityCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private Team team;

    public ShowTeamActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return team.getMembers().stream()
                .map(User::getActivityHistory)
                .map(ListingHelpers::parseList)
                .collect(Collectors.joining());
    }

    private void parseParameters(List<String> parameters) {
        team = taskManagementSystemRepository.findTeamByName(parameters.get(0));
    }
}