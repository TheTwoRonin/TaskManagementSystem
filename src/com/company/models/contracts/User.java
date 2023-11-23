package com.company.models.contracts;

import java.util.List;

public interface User extends Nameable, Loggable {

    String getName();

    void assignTask(Task task);

    void addActivity(Log activity);

    List<Task> getTasks();

    List<Log> getActivityHistory();
}
