package com.company.models.contracts;

import com.company.models.enums.Status;

import java.util.List;

public interface Task extends Identifiable {

    String getTitle();

    String getDescription();

    Status getStatus();

    List<Comment> getComments();

    List<String> getChanges();


}
