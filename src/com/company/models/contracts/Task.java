package com.company.models.contracts;

import com.company.models.enums.Status;

import java.util.List;

public interface Task extends Identifiable {

    String getTitle();

    String getDescription();

    Status getStatus();

    List<Comment> getComments();

    List<Activity> getChanges();

    void addComment(Comment comment);

    void changeStatus(Status status);

    void addActivity(Activity change);
}
