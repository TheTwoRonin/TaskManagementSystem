package com.company.core;

import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.exceptions.ElementNotFoundException;
import com.company.models.BugImpl;
import com.company.models.FeedbackImpl;
import com.company.models.StoryImpl;
import com.company.models.contracts.*;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {

    private static final String USER_NOT_FOUND_ERR = "No user with name %s";

    private int nextId;

    private final List<Task> tasks = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        this.nextId = 0;
    }


    @Override
    public Bug createBug(String title, String description, User assignee, Priority priority, Severity severity, List<String> steps) {
        Bug bug = new BugImpl(++nextId, title, description, assignee, priority, severity, steps);
        this.tasks.add(bug);
        return bug;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating);
        this.tasks.add(feedback);
        return feedback;
    }

    @Override
    public Story createStory(String title, String description, User assignee, Priority priority, Size size) {
        Story story = new StoryImpl(++nextId, title, description, assignee, priority, size);
        this.tasks.add(story);
        return story;
    }

    @Override
    public User findUserByName(String name) {
        return users.stream().filter(u -> u.getName().equals(name)).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(USER_NOT_FOUND_ERR, name)));
    }


}
