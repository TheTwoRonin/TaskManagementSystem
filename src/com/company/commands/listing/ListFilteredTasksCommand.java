package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListFilteredTasksCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private String filter;

    public ListFilteredTasksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return taskManagementSystemRepository
                .getTasks()
                .stream()
                .filter(e -> e.getTitle().contains(filter))
                .map(Object::toString)
                .collect(Collectors.joining("\n-------\n"));
    }

    private void parseParameters(List<String> parameters) {
        filter = parameters.get(0);
    }

}
