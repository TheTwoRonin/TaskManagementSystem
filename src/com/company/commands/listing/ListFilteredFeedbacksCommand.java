package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.enums.Status;
import com.company.utils.ListingHelpers;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

public class ListFilteredFeedbacksCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private Status statusFilter;


    public ListFilteredFeedbacksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return ListingHelpers.listFilteredTasks(taskManagementSystemRepository.getBugs(), statusFilter);
    }

    private void parseParameters(List<String> parameters) {
        statusFilter = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);
    }
}
