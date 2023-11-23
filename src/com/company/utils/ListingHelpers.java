package com.company.utils;

import com.company.commands.constants.CommandConstants;
import com.company.models.contracts.IntermediateTask;
import com.company.models.contracts.Task;
import com.company.models.enums.Status;

import java.util.List;
import java.util.stream.Collectors;

public class ListingHelpers {
    public static <T> String parseList(List<T> list) {
        if (list.isEmpty())
            return "No entries.";
        return list.stream().map(T::toString).collect(Collectors.joining(CommandConstants.NEW_LINE));
    }


    public static <T extends Task> String listFilteredTasks(List<T> list, Status status) {
        return list.stream()
                .filter(e -> e.getStatus().equals(status))
                .map(Object::toString)
                .collect(Collectors.joining("\n-------\n")).trim();
    }

    public static <T extends IntermediateTask> String listFilteredTasks(List<T> list, Status status, String assignee) {
        return list.stream()
                .filter(e -> e.getStatus().equals(status))
                .filter(e -> e.getAssignee().getName().equalsIgnoreCase(assignee))
                .map(Object::toString)
                .collect(Collectors.joining("\n-------\n")).trim();
    }
}