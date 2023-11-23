package com.company.commands.operations.presentation;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.ListingHelpers;

import java.util.List;

public class ShowTeamsCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowTeamsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return ListingHelpers.parseList(taskManagementSystemRepository.getTeams());
    }

}
