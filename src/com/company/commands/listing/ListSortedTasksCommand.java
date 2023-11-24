package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Task;

import java.util.List;

import static com.company.utils.ListingHelpers.listSortedTasks;

public class ListSortedTasksCommand implements Command {

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ListSortedTasksCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        return listSortedTasks(taskManagementSystemRepository.getTasks(), Task::getTitle);
    }

}
