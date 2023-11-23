package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.IntermediateTask;
import com.company.utils.ListingHelpers;

import java.util.ArrayList;
import java.util.List;

public class ListSortedTaskAssignmentsByTitleCommand implements Command {

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public ListSortedTaskAssignmentsByTitleCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        List<IntermediateTask> intermediateTaskList = new ArrayList<>(taskManagementSystemRepository.getBugs());
        intermediateTaskList.addAll(taskManagementSystemRepository.getStories());
        return ListingHelpers.listSortedTasks(intermediateTaskList, IntermediateTask::getTitle);
    }

}
