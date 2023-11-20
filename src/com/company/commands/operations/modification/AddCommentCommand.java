package com.company.commands.operations.modification;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.CommentImpl;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

public class AddCommentCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private int id;
    private String content;
    private String author;

    // TODO: 20.11.2023 г. to fix, cant add more than 1 word for content, possible solution to not check for expected number of args

    public AddCommentCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        taskManagementSystemRepository.findTaskById(id).addComment(new CommentImpl(content, author));

        return String.format(CommandConstants.COMMENT_ADDED_MESSAGE, author);
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInt(parameters.get(0), CommandConstants.INVALID_INPUT_MESSAGE);
        content = parameters.get(1);
        author = parameters.get(2);
    }
}