package com.company.models;

import com.company.models.contracts.*;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private static final int[] NAME_MIN_MAX_LENGTH = {5, 15};
    private final int id;
    private String name;
    private final List<User> members;
    private final List<Board> boards;

    TeamImpl(int id, String name) {
        this.id = id;
        //TODO Check if name unique, should be done in the Repository
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
    }


    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_MAX_LENGTH[0], NAME_MIN_MAX_LENGTH[1], "Name");
        this.name = name;
    }

    public void addMember(User user) {
        this.members.add(user);
    }

    private void addBoard(Board board) {
        this.boards.add(board);
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<User> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Board> getBoards() {
        return new ArrayList<>(boards);
    }
}
