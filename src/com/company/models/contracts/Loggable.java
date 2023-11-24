package com.company.models.contracts;

import java.util.List;

public interface Loggable {

    void addActivity(String message);

    List<Log> getActivityHistory();
}
