package com.company.models.named;

import com.company.models.Activity;
import com.company.models.contracts.Log;
import com.company.models.contracts.Task;
import com.company.models.contracts.User;
import com.company.utils.ListingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

import static com.company.commands.constants.ActivityConstants.ITEM_WITH_NAME_CREATION;
import static com.company.commands.constants.ActivityConstants.USER;

public class UserImpl implements User {
    //TODO Generate activities
    private static final int[] NAME_MIN_MAX_LENGTH = {5, 15};
    private String name;
    private final List<Task> tasks;
    private final List<Log> activityHistory;

    public UserImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
        addActivity(new Activity(ITEM_WITH_NAME_CREATION
                .formatted(USER, getName())));
    }


    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_MAX_LENGTH[0], NAME_MIN_MAX_LENGTH[1], "Name");
        this.name = name;
    }
    @Override
    public String getName() {
        return this.name;
    }

    public void assignTask(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public void addActivity(Log activity) {
        activityHistory.add(activity);
    }

    @Override
    public List<Log> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public String toString() {
        return String.format("User name: %s%n Tasks: %s", name, ListingHelpers.parseList(tasks));
    }
}
