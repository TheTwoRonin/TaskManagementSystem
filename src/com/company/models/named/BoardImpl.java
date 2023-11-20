package com.company.models.named;

import com.company.models.contracts.Board;
import com.company.models.contracts.Log;
import com.company.models.contracts.Task;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {
    //TODO Generate activities
        private static final int[] NAME_MIN_MAX_LENGTH = {5, 10};
        private String name;
        private final List<Task> tasks;
    private final List<Log> activityHistory;

        public BoardImpl(String name) {
            setName(name);
            this.tasks = new ArrayList<>();
            this.activityHistory = new ArrayList<>();
        }

        private void setName(String name) {
            ValidationHelpers.validateStringLength(name, NAME_MIN_MAX_LENGTH[0], NAME_MIN_MAX_LENGTH[1], "Name");
            this.name = name;
        }

        public void addTask(Task task) {
            tasks.add(task);
        }

        @Override
        public String getName() {
            return this.name;
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
}

