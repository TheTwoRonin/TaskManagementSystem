package com.company.commands.operations.modification;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.CommentImpl;
import com.company.models.contracts.Task;
import com.company.models.contracts.User;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandAndActivityConstants.ITEM_WITH_ID_COMMENT_ADDED;

public class AddCommentCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private int id;
    private String content;
    private String author;

    public AddCommentCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        User authorUser = taskManagementSystemRepository.findUserByName(author);
        Task task = taskManagementSystemRepository.findTaskById(id);

        task.addComment(new CommentImpl(content, author));
        String log = ITEM_WITH_ID_COMMENT_ADDED
                .formatted(task.getClass().getSimpleName().replaceAll("Impl", ""),
                        task.getId(),
                        content,
                        author);
        authorUser.addActivity(log);
        task.addActivity(log);

        return String.format(CommandAndActivityConstants.COMMENT_ADDED_MESSAGE, author);
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInt(parameters.get(0), CommandAndActivityConstants.INVALID_INPUT_MESSAGE);
        content = parameters.get(1);
        author = parameters.get(2);
    }
}