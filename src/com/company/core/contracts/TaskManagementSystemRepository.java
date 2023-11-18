package com.company.core.contracts;

import com.company.models.contracts.Bug;
import com.company.models.contracts.Comment;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;

import java.util.List;

public interface TaskManagementSystemRepository {

    Bug createBug(String title, String description, User assignee, Priority priority, Severity severity, List<String> steps);

    User findUserByName(String name);
}
