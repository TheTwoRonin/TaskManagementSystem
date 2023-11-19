package com.company.models.contracts;

import java.util.List;

public interface User extends Nameable{

    String getName();

    void assignTask(Task task);

    void addActivity(Activity activity);

    List<Task> getTasks();

    List<Activity> getActivityHistory();
}
