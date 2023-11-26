package com.company.models.contracts;

import java.util.List;

public interface User extends Nameable, Loggable, Comparable<User> {

    String getName();

    void assignTask(Task task);

    void unassignTask(Task task);

    List<Task> getTasks();

    List<Log> getActivityHistory();
}
