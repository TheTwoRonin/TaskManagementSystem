package com.company.commands.operations.presentation;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Task;
import com.company.utils.ListingHelpers;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.commands.constants.CommandAndActivityConstants.INVALID_INPUT_MESSAGE;

public class ShowTaskActivityCommand implements Command {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private Task task;

    public ShowTaskActivityCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return ListingHelpers.parseList(task.getActivityHistory());
    }

    private void parseParameters(List<String> parameters) {
        task = taskManagementSystemRepository.findTaskById(ParsingHelpers.tryParseInt(parameters.get(0), INVALID_INPUT_MESSAGE));
    }
}
