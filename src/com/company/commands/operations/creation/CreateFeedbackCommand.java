package com.company.commands.operations.creation;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Board;
import com.company.models.contracts.Feedback;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandConstants.FEEDBACK;

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
        return String.format(CommandConstants.TASK_CREATED_MESSAGE, FEEDBACK, createdFeedback.getId());
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(0);
        description = parameters.get(1);
        rating = ParsingHelpers.tryParseInt(parameters.get(2), CommandConstants.INVALID_INPUT_MESSAGE);
        board = taskManagementSystemRepository.findBoardByName(parameters.get(3));
    }
}