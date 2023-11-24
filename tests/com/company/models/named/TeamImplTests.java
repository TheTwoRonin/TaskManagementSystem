package com.company.models.named;

import com.company.models.contracts.Board;
import com.company.models.contracts.Team;
import com.company.models.contracts.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.utils.NamingConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TeamImplTests {

    private Team team;

    private User user;

    private Board board;

    @BeforeEach
    public void setUp() {
        team = new TeamImpl(VALID_NAME);
        user = UserImplTests.initializeTestUser();
        board = BoardImplTests.initializeTestBoard();
    }

    @Test
    public void should_CreateUser_When_ArgumentsAreValid() {
        assertEquals(VALID_NAME, team.getName());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {
        assertThrows(IllegalArgumentException.class, () -> new UserImpl(INVALID_NAME_TOO_SHORT));
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsLongerThanExpected() {
        assertThrows(IllegalArgumentException.class, () -> new UserImpl(INVALID_NAME_TOO_LONG));
    }

    @Test
    public void should_addMember_When_ArgumentsAreValid() {

        team.addMember(user);

        assertEquals(1, team.getMembers().size());
    }

    @Test
    public void should_addBoard_When_ArgumentsAreValid() {

        team.addBoard(board);

        assertEquals(1, team.getBoards().size());
    }

    @Test
    public void getMembers_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(team.getMembers(),team.getMembers());
    }

    @Test
    public void getBoards_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(team.getBoards(),team.getBoards());
    }

    public static User initializeTestTeam() {
        return new UserImpl(VALID_NAME);
    }
}
