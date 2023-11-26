package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Story;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.utils.ListingHelpers.listSortedTasks;

public class ListSortedStoriesByFieldCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private String sortType;

    public ListSortedStoriesByFieldCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return getSortField(taskManagementSystemRepository.getStories(), sortType);
    }

    private void parseParameters(List<String> parameters) {
        sortType = parameters.get(0).toLowerCase();
    }

    private String getSortField(List<Story> list, String sortType) {
        switch (sortType) {
            case "title":
                return listSortedTasks(list, Story::getTitle);
            case "description":
                return listSortedTasks(list, Story::getDescription);
            case "assignee":
                return listSortedTasks(list, Story::getAssignee);
            case "status":
                return listSortedTasks(list, Story::getStatus);
            case "priority":
                return listSortedTasks(list, Story::getPriority);
            case "size":
                return listSortedTasks(list, Story::getSize);
            default:
                throw new IllegalArgumentException("Invalid sorter");
        }
    }

}
