package com.company.models.contracts;

import com.company.models.enums.Status;

import java.util.List;

public interface Task extends Identifiable, Loggable {

    String getTitle();

    String getDescription();

    Status getStatus();

    List<Comment> getComments();

    void addComment(Comment comment);

    void changeStatus(Status status);
}
