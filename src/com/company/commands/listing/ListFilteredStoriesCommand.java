package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.enums.Status;
import com.company.utils.ListingHelpers;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;

public class ListFilteredStoriesCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private Status statusFilter;
    private String assigneeFilter;

    public ListFilteredStoriesCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return parameters.size() == 2 ?
                ListingHelpers.listFilteredTasks(taskManagementSystemRepository.getStories(),
                        statusFilter, assigneeFilter) :
                ListingHelpers.listFilteredTasks(taskManagementSystemRepository.getStories(), statusFilter);
    }

    private void parseParameters(List<String> parameters) {
        statusFilter = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);
        assigneeFilter = parameters.size() == 2 ? parameters.get(1) : null;
    }
}