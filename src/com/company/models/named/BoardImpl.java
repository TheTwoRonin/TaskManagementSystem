package com.company.models.named;

import com.company.models.Activity;
import com.company.models.contracts.Board;
import com.company.models.contracts.Log;
import com.company.models.contracts.Task;
import com.company.utils.ListingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    private static final int[] NAME_MIN_MAX_LENGTH = {5, 10};
    private static final String TO_STRING = "%nBoard name: %s%nTasks:%n%s";
    private static final String NAME = "Name";
    private final List<Task> tasks;
    private String name;
    private final List<Log> activityHistory;

    public BoardImpl(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_MAX_LENGTH[0], NAME_MIN_MAX_LENGTH[1], NAME);
        this.name = name;
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public void addActivity(String message) {
        activityHistory.add(new Activity(message));
    }

    @Override
    public List<Log> getActivityHistory() {
        return new ArrayList<>(activityHistory);
    }

    @Override
    public String toString() {
        return String.format(TO_STRING, name, ListingHelpers.parseList(tasks));
    }
}

