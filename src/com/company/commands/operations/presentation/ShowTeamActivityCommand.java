package com.company.commands.operations.presentation;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Team;
import com.company.models.contracts.User;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

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
        StringBuilder sb = new StringBuilder();
        for (User member : team.getMembers()) {
            sb.append(ParsingHelpers.tryParseList(member.getActivityHistory()));
        }

        return sb.toString();
    }

    private void parseParameters(List<String> parameters) {
        team = taskManagementSystemRepository.findTeamByName(parameters.get(0));
    }
}


//        return team.getMembers().stream().map(User::getActivityHistory).map(ParsingHelpers::tryParseList).collect(Collectors.joining());