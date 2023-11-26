package com.company.models.named;

import com.company.models.contracts.Board;
import com.company.models.contracts.Team;
import com.company.models.contracts.User;
import com.company.utils.ListingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.ArrayList;
import java.util.List;

public class TeamImpl implements Team {
    private static final int[] NAME_MIN_MAX_LENGTH = {5, 15};
    private static final String TO_STRING = "Team name: %s%nMembers: %n%s%nBoards: %n%s%n";
    private static final String NAME = "Name";
    private String name;
    private final List<User> members;
    private final List<Board> boards;

    public TeamImpl(String name) {
        setName(name);
        this.members = new ArrayList<>();
        this.boards = new ArrayList<>();
    }

    private void setName(String name) {
        ValidationHelpers.validateStringLength(name, NAME_MIN_MAX_LENGTH[0], NAME_MIN_MAX_LENGTH[1], NAME);
        this.name = name;
    }

    public void addMember(User user) {
        this.members.add(user);
    }

    public void addBoard(Board board) {
        this.boards.add(board);
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

    @Override
    public String toString() {
        return String.format(TO_STRING, name,
                ListingHelpers.parseList(members),
                ListingHelpers.parseList(boards));
    }
}
