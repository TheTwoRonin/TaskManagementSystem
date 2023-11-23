package com.company.core.contracts;

import com.company.models.contracts.*;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Size;

import java.util.List;

public interface TaskManagementSystemRepository {

    List<Task> getTasks();

    List<Bug> getBugs();

    List<Story> getStories();

    List<Feedback> getFeedbacks();

    List<User> getUsers();

    List<Board> getBoards();

    List<Team> getTeams();

    Bug createBug(String title, String description, User assignee, Priority priority, Severity severity, List<String> steps);

    Feedback createFeedback(String title, String description, int rating);

    Story createStory(String title, String description, User assignee, Priority priority, Size size);

    User createUser(String name);

    Team createTeam(String name);

    Board createBoard(String name, Team team);

    User findUserByName(String name);

    Team findTeamByName(String name);

    Board findBoardByName(String name);

    void userIsUnique(String name);

    void teamIsUnique(String name);

    //void boardIsUniqueInTeam(String name);
    void boardIsUniqueInTeam(String name, Team team);
    //TODO Implement generics
    //<T> T findByName(String name);
    // <T> void isUnique(List<T> elements, String name);

    Task findTaskById(int id);

    Bug findBugById(int id);

    Story findStoryById(int id);

    Feedback findFeedbackById(int id);

    TaskAssignment findTaskAssignmentById(int id);
}
