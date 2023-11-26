package com.company.commands.operations.creation;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Board;
import com.company.models.contracts.Bug;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.commands.constants.CommandAndActivityConstants.*;

public class CreateBugCommand implements Command {


    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 7;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private String title;
    private String description;
    private User assignee;
    private Priority priority;
    private Severity severity;
    private List<String> stepsList;
    private Board board;

    public CreateBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Bug createdBug = taskManagementSystemRepository.createBug(title, description, assignee, priority,
                severity, stepsList);

        assignee.assignTask(createdBug);

        assignee.addActivity(ITEM_WITH_ID_ASSIGNED_TO_USER
                .formatted(BUG, createdBug.getId(), assignee.getName()));

        board.addTask(createdBug);

        board.addActivity(ITEM_WITH_ID_ADDED_TO_BOARD
                .formatted(BUG, createdBug.getId(), board.getName()));

        return String.format(CommandAndActivityConstants.TASK_CREATED_MESSAGE, BUG, createdBug.getId());
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(0);
        description = parameters.get(1);
        assignee = taskManagementSystemRepository.findUserByName(parameters.get(2));
        priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
        severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class);
        stepsList = Arrays.stream(parameters.get(5).split("((?=\\d\\.))")).collect(Collectors.toList());
        board = taskManagementSystemRepository.findBoardByName(parameters.get(6));
    }

}
