package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Feedback;
import com.company.utils.ValidationHelpers;

import java.util.List;

import static com.company.utils.ListingHelpers.listSortedTasks;

public class ListSortedFeedbackByFieldCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private String sortType;

    public ListSortedFeedbackByFieldCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return getSortField(taskManagementSystemRepository.getFeedback(), sortType);
    }

    private void parseParameters(List<String> parameters) {
        sortType = parameters.get(0).toLowerCase();
    }

    private String getSortField(List<Feedback> list, String sortType) {
        switch (sortType) {
            case "title":
                return listSortedTasks(list, Feedback::getTitle);
            case "description":
                return listSortedTasks(list, Feedback::getDescription);
            case "rating":
                return listSortedTasks(list, Feedback::getRating);
            case "status":
                return listSortedTasks(list, Feedback::getStatus);
            default:
                throw new UnsupportedOperationException("Ne moesh da sortirash po tova, ludiq");
        }
    }

}
