package com.company.models.contracts;

import java.util.List;

public interface Team extends Identifiable{

    String getName();

    List<User> getMembers();

    List<Board> getBoards();

}
