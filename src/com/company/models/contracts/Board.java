package com.company.models.contracts;

import java.util.List;

public interface Board extends Nameable, Loggable {

    String getName();

    void addTask(Task task);

    List<Task> getTasks();

    List<Log> getActivityHistory();
}
