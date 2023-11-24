package com.company.models.named;

import com.company.models.Activity;
import com.company.models.contracts.Board;
import com.company.models.contracts.Story;
import com.company.models.idd.StoryImplTests;
import com.company.utils.TaskBaseConstraints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.utils.NamingConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardImplTests {

    private Board board;
    private Story story;


    @BeforeEach
    public void setUp() {
        board = new BoardImpl(VALID_BOARD_NAME);
        story = StoryImplTests.initializeTestStory();
        board.addTask(story);
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
        assertEquals(1, board.getTasks().size());
    }

    @Test
    public void should_addActivity_When_ArgumentsAreValid() {
        board.addActivity(new Activity(TaskBaseConstraints.VALID_DESCRIPTION));
        assertEquals(1, board.getActivityHistory().size());
    }

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
