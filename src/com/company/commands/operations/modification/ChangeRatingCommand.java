package com.company.commands.operations.modification;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Feedback;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

public class ChangeRatingCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private int id;
    private int rating;

    public ChangeRatingCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Feedback feedback = taskManagementSystemRepository.findFeedbackById(id);
        feedback.changeRating(rating);
        return String.format(CommandConstants.ENUM_CHANGED_MESSAGE, CommandConstants.PRIORITY, id);
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_INPUT_MESSAGE);
        rating = ParsingHelpers.tryParseInt(parameters.get(1), CommandConstants.INVALID_INPUT_MESSAGE);
    }
}