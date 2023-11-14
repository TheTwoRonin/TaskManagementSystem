package com.company.models.contracts;

import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class BoardImpl implements Board {

        private static final int[] NAME_MIN_MAX_LENGTH = {5, 10};
        private final int id;
        private String name;
        private final List<Task> tasks;
        private final List<Activity> activityHistory;

        BoardImpl(int id, String name) {
            this.id = id;
            //TODO Check if name unique, should be done in the Repository
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
        public int getId() {
            return this.id;
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

