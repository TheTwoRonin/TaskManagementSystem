package com.company.commands.operations;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Board;
import com.company.models.contracts.Team;
import com.company.utils.ValidationHelpers;
import java.util.List;
import static com.company.commands.constants.CommandConstants.BOARD;

public class CreateBoardCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private String name;
    private Team team;

    public CreateBoardCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        taskManagementSystemRepository.boardIsUniqueInTeam(name, team);

        Board createdBoard = taskManagementSystemRepository.createBoard(name, team);

        return String.format(CommandConstants.CREATED_W_NAME_MESSAGE, BOARD, createdBoard.getName());
    }

    private void parseParameters(List<String> parameters) {
        name = parameters.get(0);
        team = taskManagementSystemRepository.findTeamByName(parameters.get(1));
    }

}
