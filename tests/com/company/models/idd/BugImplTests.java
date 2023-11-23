package com.company.models.idd;

import com.company.models.contracts.Bug;
import com.company.models.enums.Priority;
import com.company.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.*;

public class BugImplTests {
    private Bug bug;

    @BeforeEach
    public void setUp() {
        bug = new BugImpl(VALID_ID, VALID_TITLE, VALID_DESCRIPTION, VALID_ASSIGNEE,
                VALID_PRIORITY, VALID_SEVERITY, VALID_STEPS);
    }

    @Test
    public void should_CreateBug_When_ArgumentsAreValid() {
        assertAll(
                () -> assertEquals(VALID_ID, bug.getId()),
                () -> assertEquals(VALID_TITLE, bug.getTitle()),
                () -> assertEquals(VALID_DESCRIPTION, bug.getDescription()),
                () -> assertEquals(VALID_ASSIGNEE, bug.getAssignee()),
                () -> assertEquals(VALID_PRIORITY, bug.getPriority()),
                () -> assertEquals(VALID_SEVERITY, bug.getSeverity()),
                () -> assertEquals(VALID_STEPS, bug.getSteps()));
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleIsShorterThanExpected() {
        assertThrows(IllegalArgumentException.class, () -> new BugImpl(VALID_ID, INVALID_TITLE,
                VALID_DESCRIPTION, VALID_ASSIGNEE, VALID_PRIORITY,
                VALID_SEVERITY, VALID_STEPS));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsShorterThanExpected() {
        assertThrows(IllegalArgumentException.class, () -> new BugImpl(VALID_ID, VALID_TITLE,
                INVALID_DESCRIPTION, VALID_ASSIGNEE, VALID_PRIORITY,
                VALID_SEVERITY, VALID_STEPS));
    }

    @Test
    public void changeStatus_Should_ChangeStatus_When_ValidStatus() {
        bug.changeStatus(Status.DONE);
        assertEquals(Status.DONE, bug.getStatus());
    }

    @Test
    public void changePriority_Should_ChangePriority_When_ValidPriority() {
        bug.changePriority(Priority.LOW);
        assertEquals(Priority.LOW, bug.getPriority());
    }

    @Test
    public void changeStatus_Should_ThrowException_When_InvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> bug.changeStatus(Status.UNSCHEDULED));
    }

    @Test
    public void getSteps_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(bug.getSteps(), bug.getSteps());
    }

    @Test
    public void getComments_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(bug.getComments(), bug.getComments());
    }

    @Test
    public void addComment_Should_AddCommentToTheCollection() {
        bug.addComment(VALID_COMMENT);
        assertEquals(VALID_COMMENT, bug.getComments().get(0));
    }

    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {
        Assertions.assertNotSame(bug.getActivityHistory(), bug.getActivityHistory());
    }

    @Test
    public void getActivityHistory_Should_Have_Item_After_Creation() {
        Assertions.assertEquals(1, bug.getActivityHistory().size());
    }
}