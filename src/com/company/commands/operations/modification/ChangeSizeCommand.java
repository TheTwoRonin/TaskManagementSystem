package com.company.commands.operations.modification;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Story;
import com.company.models.enums.Size;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

public class ChangeSizeCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private int id;
    private Size size;

    public ChangeSizeCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Story story = taskManagementSystemRepository.findStoryById(id);

        story.changeSize(size);
        return String.format(CommandAndActivityConstants.ENUM_CHANGED_MESSAGE, CommandAndActivityConstants.SEVERITY, id);
    }

    private void parseParameters(List<String> parameters) {
        id = ParsingHelpers.tryParseInt(parameters.get(0), CommandAndActivityConstants.INVALID_INPUT_MESSAGE);
        size = ParsingHelpers.tryParseEnum(parameters.get(1), Size.class);
    }
}