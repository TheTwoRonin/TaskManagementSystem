package com.company.core;

import com.company.commands.constants.CommandConstants;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.exceptions.ElementNotFoundException;
import com.company.models.*;
import com.company.models.contracts.*;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Size;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {

    private int nextId;

    private final List<Task> tasks = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    private final List<Board> boards = new ArrayList<>();
    private final List<Team> teams = new ArrayList<>();

    public TaskManagementSystemRepositoryImpl() {
        this.nextId = 0;
    }


    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
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
    public User createUser(String name) {
        User user = new UserImpl(name);
        this.users.add(user);
        return user;
    }

    @Override
    public Team createTeam(String name) {
        Team team = new TeamImpl(name);
        this.teams.add(team);
        return team;
    }

    @Override
    public Board createBoard(String name) {
        Board board = new BoardImpl(name);
        this.boards.add(board);
        return board;
    }

    @Override
    public User findUserByName(String name) {
        return users.stream().filter(u -> u.getName().equals(name)).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CommandConstants.USER_NOT_FOUND_ERR, name)));
    }

    @Override
    public Team findTeamByName(String name) {
        return teams.stream().filter(t -> t.getName().equals(name)).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CommandConstants.TEAM_NOT_FOUND_ERR, name)));
    }

    @Override
    public Board findBoardByName(String name) {
        return boards.stream().filter(b -> b.getName().equals(name)).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CommandConstants.BOARD_NOT_FOUND_ERR, name)));
    }


    @Override
    public void userIsUnique(String name) {
        try{
            if(findUserByName(name).getName().equals(name))
           throw new IllegalArgumentException("User with this name has been already created.");
        }
        catch (ElementNotFoundException ignored){}
    }
    @Override
    public void teamIsUnique(String name) {
        try{
            if(findTeamByName(name).getName().equals(name))
                throw new IllegalArgumentException("Team with this name has been already created.");
        }
        catch (ElementNotFoundException ignored){}
    }

    @Override
    public Task findTaskById(int id) {
        return tasks.stream().filter(t -> t.getId() == id).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CommandConstants.TASK_NOT_FOUND_ERR, id)));
    }
}
