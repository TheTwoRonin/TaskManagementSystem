package com.company.commands.operations.presentation;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.ParsingHelpers;

import java.util.List;

public class ShowUsersCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 0;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ShowUsersCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return ParsingHelpers.tryParseList(taskManagementSystemRepository.getUsers());
    }

}
