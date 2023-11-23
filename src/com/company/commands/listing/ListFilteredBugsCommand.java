package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.enums.Status;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.List;
import java.util.stream.Collectors;

public class ListFilteredBugsCommand implements Command {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private final TaskManagementSystemRepository taskManagementSystemRepository;
    private Status statusFilter;
    private String assigneeFilter;

    public ListFilteredBugsCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        return parameters.size() == 2 ? taskManagementSystemRepository.getBugs().stream()
                .filter(e -> e.getStatus().equals(statusFilter))
                .filter(e -> e.getAssignee().getName().equalsIgnoreCase(assigneeFilter))
                .map(Object::toString)
                .collect(Collectors.joining("\n-------\n")) :
                taskManagementSystemRepository.getTasks().stream()
                        .filter(e -> e.getStatus().equals(statusFilter))
                        .map(Object::toString)
                        .collect(Collectors.joining("\n-------\n")).trim();
    }

    private void parseParameters(List<String> parameters) {
        statusFilter = ParsingHelpers.tryParseEnum(parameters.get(0), Status.class);
        assigneeFilter = parameters.size() == 2 ? parameters.get(1) : null;
    }
}
