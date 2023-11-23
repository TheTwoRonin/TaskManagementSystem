package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Bug;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.utils.ListingHelpers.listSortedTasks;

public class ListSortedBugsByFieldCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private String sortType;

    public ListSortedBugsByFieldCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return getSortField(taskManagementSystemRepository.getBugs(), sortType);
    }

    private void parseParameters(List<String> parameters) {
        sortType = parameters.get(1).toLowerCase();
    }

    private String getSortField(List<Bug> list, String sortType) {
        switch (sortType) {
            case "title":
                return listSortedTasks(list, Bug::getTitle);
            case "description":
                return listSortedTasks(list, Bug::getStatus);
            case "assignee":
                return listSortedTasks(list, Bug::getAssignee);
            case "priority":
                return listSortedTasks(list, Bug::getPriority);
            case "severity":
                return listSortedTasks(list, Bug::getSeverity);
            default:
                throw new UnsupportedOperationException("Ne moesh da sortirash po tova, ludiq");
        }
    }


}
