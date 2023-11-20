package com.company.models.contracts;

import java.util.List;

public interface Loggable {

    void addActivity(Log activity);

    List<Log> getActivityHistory();
}
