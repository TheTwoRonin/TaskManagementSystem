package com.company.commands.operations.creation;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Size;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandConstants.STORY;

public class CreateStoryCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private String title;
    private String description;
    private User assignee;
    private Priority priority;
    private Size size;

    public CreateStoryCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Story createdStory = taskManagementSystemRepository.createStory(title, description, assignee, priority, size);
        // TODO: 19.11.2023 г. add task to user
//        assignee.addTask(createdStory);
        // TODO: 20.11.2023 г. add task to board

        return String.format(CommandConstants.TASK_CREATED_MESSAGE, STORY, createdStory.getId());
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(0);
        description = parameters.get(1);
        assignee = taskManagementSystemRepository.findUserByName(parameters.get(2));
        priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
        size = ParsingHelpers.tryParseEnum(parameters.get(4), Size.class);
    }

}
