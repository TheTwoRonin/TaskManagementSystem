package com.company.core;

import com.company.commands.constants.CommandConstants;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.exceptions.ElementNotFoundException;
import com.company.models.contracts.*;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Size;
import com.company.models.idd.BugImpl;
import com.company.models.idd.FeedbackImpl;
import com.company.models.idd.StoryImpl;
import com.company.models.named.BoardImpl;
import com.company.models.named.TeamImpl;
import com.company.models.named.UserImpl;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementSystemRepositoryImpl implements TaskManagementSystemRepository {

    private int nextId;
    // TODO: 20.11.2023 г. 3 lists for tasks // 4th list with all tasks // remove all casting
    private final List<Task> tasks = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();
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
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Feedback> getFeedback() {
        return new ArrayList<>(feedbacks);
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
        this.bugs.add(bug);

        return bug;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating);
        this.tasks.add(feedback);
        this.feedbacks.add(feedback);

        return feedback;
    }

    @Override
    public Story createStory(String title, String description, User assignee, Priority priority, Size size) {
        Story story = new StoryImpl(++nextId, title, description, assignee, priority, size);
        this.tasks.add(story);
        this.stories.add(story);

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
    public Board createBoard(String name, Team team) {
        Board board = new BoardImpl(name);
        team.addBoard(board);
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
        if(users.stream().anyMatch(u -> u.getName().equals(name))){
            throw new IllegalArgumentException("User with this name has been already created.");
        }
    }

    @Override
    public void teamIsUnique(String name) {
        if (teams.stream().anyMatch(t -> t.getName().equals(name))) {
            throw new IllegalArgumentException("Team with this name has been already created.");
        }
    }

    @Override
    public void boardIsUniqueInTeam(String name, Team team) {
        List<Board> teamBoards = team.getBoards();
        if (teamBoards.stream().anyMatch(b -> b.getName().equals(name))) {
            throw new IllegalArgumentException("Board with this name has been already created.");
        }
    }

    //TODO Rebuild to work for all
    @Override
    public Task findTaskById(int id) {
        return findTaskById(id, tasks);
    }

    @Override
    public Bug findBugById(int id) {
        return findTaskById(id, bugs);
    }

    @Override
    public Story findStoryById(int id) {
        return findTaskById(id, stories);
    }

    @Override
    public Feedback findFeedbackById(int id) {
        return findTaskById(id, feedbacks);
    }

    @Override
    public TaskAssignment findTaskAssignmentById(int id) {//
        List<TaskAssignment> taskAssignmentList = new ArrayList<>(bugs);
        taskAssignmentList.addAll(stories);
        return findTaskById(id, taskAssignmentList);
    }

    private <T extends Task> T findTaskById(int id, List<T> tasks) {
        return tasks.stream().filter(t -> t.getId() == id).findAny()
                .orElseThrow(() -> new ElementNotFoundException(String.format(CommandConstants.TASK_NOT_FOUND_ERR, id)));
    }
}
