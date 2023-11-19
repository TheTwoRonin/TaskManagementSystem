package com.company.models.contracts;

import java.util.List;

public interface Team extends Nameable{

    String getName();

    void addMember(User user);

    void addBoard(Board board);

    List<User> getMembers();

    List<Board> getBoards();

}
