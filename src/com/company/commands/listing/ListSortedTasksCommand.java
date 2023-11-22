package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListSortedTasksCommand implements Command {

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ListSortedTasksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        return taskManagementSystemRepository
                .getTasks()
                .stream()
                .sorted(Comparator.comparing(Task::getTitle))
                .map(Object::toString)
                .collect(Collectors.joining("\n-------\n"));
    }

}
