package com.company.models.named;

import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import com.company.models.idd.StoryImplTests;
import com.company.utils.TaskBaseConstraints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.utils.NamingConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserImplTests {

    private User user;
    private Story story;


    @BeforeEach
    public void setUp() {
        user = initializeTestUser();
        story = StoryImplTests.initializeTestStory();
        user.assignTask(story);
    }

    @Test
    public void should_CreateUser_When_ArgumentsAreValid() {
        assertEquals(VALID_NAME, user.getName());
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
    public void should_assignTask_When_ArgumentsAreValid() {
        assertEquals(1, user.getTasks().size());
    }

    @Test
    public void should_unassignTask_When_ArgumentsAreValid() {
        user.unassignTask(story);
        assertEquals(0, user.getTasks().size());
    }

    @Test
    public void should_addActivity_When_ArgumentsAreValid() {
        user.addActivity(TaskBaseConstraints.VALID_DESCRIPTION);
        assertEquals(2, user.getActivityHistory().size());
    }

    @Test
    public void getTasks_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(user.getTasks(), user.getTasks());
    }

    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(user.getActivityHistory(),user.getActivityHistory());
    }

    public static User initializeTestUser() {
        return new UserImpl(VALID_NAME);
    }
}
