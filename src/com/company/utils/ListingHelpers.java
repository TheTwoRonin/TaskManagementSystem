package com.company.utils;

import com.company.commands.constants.CommandAndActivityConstants;
import com.company.models.contracts.Task;
import com.company.models.contracts.TaskAssignment;
import com.company.models.enums.Status;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListingHelpers {

    private static final String NO_ENTRIES = "No entries.";

    public static <T> String parseList(List<T> list) {
        if (list.isEmpty())
            return NO_ENTRIES;
        return list.stream().map(T::toString).collect(Collectors.joining(CommandAndActivityConstants.NEW_LINE));
    }


    public static <T extends Task> String listFilteredTasks(List<T> list, Status status) {
        return list.stream()
                .filter(e -> e.getStatus().equals(status))
                .map(Object::toString)
                .collect(Collectors.joining(CommandAndActivityConstants.NEW_LINE)).trim();
    }

    public static <T extends TaskAssignment> String listFilteredTasks(List<T> list, Status status, String assignee) {
        return list.stream()
                .filter(e -> e.getStatus().equals(status))
                .filter(e -> e.getAssignee().getName().equalsIgnoreCase(assignee))
                .map(Object::toString)
                .collect(Collectors.joining(CommandAndActivityConstants.NEW_LINE)).trim();
    }


    public static <T, R extends Comparable<? super R>> String listSortedTasks(List<T> list, Function<T, R> sortFunction) {
        return list.stream()
                .sorted(Comparator.comparing(sortFunction))
                .map(Object::toString)
                .collect(Collectors.joining(CommandAndActivityConstants.NEW_LINE)).trim();
    }
}