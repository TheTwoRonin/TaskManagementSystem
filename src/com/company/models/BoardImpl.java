package com.company.models;

import com.company.models.contracts.Activity;
import com.company.models.contracts.Board;
import com.company.models.contracts.Task;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

        private static final int[] NAME_MIN_MAX_LENGTH = {5, 10};
        private String name;
        private final List<Task> tasks;
        private final List<Activity> activityHistory;

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

        private void addActivity(Activity activity) {
            activityHistory.add(activity);
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
        public List<Activity> getActivityHistory() {
            return new ArrayList<>(activityHistory);
        }
}

