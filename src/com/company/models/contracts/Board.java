package com.company.models.contracts;

import java.util.List;

public interface Board extends Identifiable{

    String getName();

    List<Task> getTasks();

    List<Activity> getActivityHistory();
}