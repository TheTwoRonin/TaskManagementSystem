package com.company.models.named;

import com.company.models.contracts.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.utils.NamingConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardImplTests {
    private Board board;

    //TODO Test assignTask
    //private Story story;
    //TODO Test addActivity

    @BeforeEach
    public void setUp() {
        board = new BoardImpl(VALID_BOARD_NAME);
    }

    @Test
    public void should_CreateBoard_When_ArgumentsAreValid() {
        assertEquals(VALID_BOARD_NAME, board.getName());
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsShorterThanExpected() {
        assertThrows(IllegalArgumentException.class, () -> new BoardImpl(INVALID_NAME_TOO_SHORT));
    }

    @Test
    public void constructor_Should_ThrowException_When_NameIsLongerThanExpected() {
        assertThrows(IllegalArgumentException.class, () -> new BoardImpl(INVALID_NAME_TOO_LONG));
    }

    @Test
    public void should_assignTask_When_ArgumentsAreValid() {
    }

    @Test
    public void should_addActivity_When_ArgumentsAreValid() {    }

    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(board.getTasks(),board.getTasks());
    }

    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(board.getActivityHistory(),board.getActivityHistory());
    }


    public static Board initializeTestBoard() {
        return new BoardImpl(VALID_BOARD_NAME);
    }
}
