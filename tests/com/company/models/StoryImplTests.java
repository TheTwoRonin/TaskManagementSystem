package com.company.models;

import com.company.models.contracts.Story;
import com.company.models.enums.Status;
import com.company.models.idd.StoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StoryImplTests {

    private Story story;

    @BeforeEach
    public void setUp() {
        story = new StoryImpl(VALID_ID, VALID_TITLE, VALID_DESCRIPTION, VALID_ASSIGNEE, VALID_PRIORITY, VALID_SIZE);
    }

    @Test
    public void should_CreateBug_When_ArgumentsAreValid() {
        assertAll(
                () -> assertEquals(VALID_ID, story.getId()),
                () -> assertEquals(VALID_TITLE, story.getTitle()),
                () -> assertEquals(VALID_DESCRIPTION, story.getDescription()),
                () -> assertEquals(VALID_ASSIGNEE, story.getAssignee()),
                () -> assertEquals(VALID_PRIORITY, story.getPriority()),
                () -> assertEquals(VALID_SIZE, story.getSize()));
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StoryImpl(VALID_ID, INVALID_TITLE,
                VALID_DESCRIPTION, VALID_ASSIGNEE, VALID_PRIORITY, VALID_SIZE));
    }

    @Test
    public void constructor_Should_InitializeDescription_When_ArgumentsAreValid() {
        Assertions.assertEquals(VALID_DESCRIPTION, story.getDescription());
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new StoryImpl(VALID_ID, VALID_TITLE,
                INVALID_DESCRIPTION, VALID_ASSIGNEE, VALID_PRIORITY, VALID_SIZE));
    }

    @Test
    public void status_Should_ChangeStatus_When_ValidStatus() {
        story.changeStatus(Status.DONE);
        Assertions.assertEquals(Status.DONE, story.getStatus());
    }

    @Test
    public void status_Should_ThrowException_When_InvalidStatus() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> story.changeStatus(Status.UNSCHEDULED));
    }

    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(story.getActivityHistory(), story.getActivityHistory());
    }
    @Test
    public void getActivityHistory_Should_Have_Item_After_Creation() {
        Assertions.assertEquals(1, story.getActivityHistory().size());
    }
}
