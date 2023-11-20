package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;

import java.util.List;

public class ListTasksCommand implements Command {
    TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();


    @Override
    public String execute(List<String> parameters) {
        return null;
    }
}
