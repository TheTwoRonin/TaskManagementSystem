package com.company.commands.operations.creation;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Board;
import com.company.models.contracts.Feedback;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandAndActivityConstants.FEEDBACK;
import static com.company.commands.constants.CommandAndActivityConstants.ITEM_WITH_ID_ADDED_TO_BOARD;

public class CreateFeedbackCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 4;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private String title;
    private String description;
    private int rating;
    private Board board;

    public CreateFeedbackCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Feedback createdFeedback = taskManagementSystemRepository.createFeedback(title, description, rating);
        board.addTask(createdFeedback);
        board.addActivity(ITEM_WITH_ID_ADDED_TO_BOARD
                .formatted(FEEDBACK, createdFeedback.getId(), board.getName()));
        return String.format(CommandAndActivityConstants.TASK_CREATED_MESSAGE, FEEDBACK, createdFeedback.getId());
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(0);
        description = parameters.get(1);
        rating = ParsingHelpers.tryParseInt(parameters.get(2), CommandAndActivityConstants.INVALID_INPUT_MESSAGE);
        board = taskManagementSystemRepository.findBoardByName(parameters.get(3));
    }
}