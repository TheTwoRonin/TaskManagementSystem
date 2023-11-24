package com.company.commands.operations.presentation;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Board;
import com.company.utils.ListingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

public class ShowBoardActivityCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private Board board;

    public ShowBoardActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return ListingHelpers.parseList(board.getActivityHistory());
    }

    private void parseParameters(List<String> parameters) {
        board = taskManagementSystemRepository.findBoardByName(parameters.get(0));
    }
}
