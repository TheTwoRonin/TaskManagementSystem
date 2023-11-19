package com.company.core.contracts;

import com.company.models.contracts.*;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Size;

import java.util.List;

public interface TaskManagementSystemRepository {

    Bug createBug(String title, String description, User assignee, Priority priority, Severity severity, List<String> steps);

    Feedback createFeedback(String title, String description, int rating);

    Story createStory(String title, String description, User assignee, Priority priority, Size size);

    User findUserByName(String name);

    Task findTaskById(int id);
}
