package com.company.models.contracts;

import java.util.List;

public interface Board extends Nameable, Loggable {

    String getName();

    List<Task> getTasks();

    List<Log> getActivityHistory();
}
