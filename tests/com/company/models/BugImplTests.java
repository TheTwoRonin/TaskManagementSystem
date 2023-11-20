package com.company.models;

import com.company.models.contracts.Bug;
import com.company.models.enums.Priority;
import com.company.models.enums.Status;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        bug = new BugImpl(VALID_ID, VALID_TITLE, VALID_DESCRIPTION, VALID_ASSIGNEE,
                VALID_PRIORITY, VALID_SEVERITY, new ArrayList<>());
        bug.getSteps().add(TestUtilities.getString(5));

        assertEquals(0, bug.getSteps().size());
    }

    @Test
    public void getComments_Should_ReturnCopyOfTheCollection() {
        bug.getComments().add(VALID_COMMENT);

        assertEquals(0, bug.getComments().size());
    }

    @Test
    public void addComment_Should_AddCommentToTheCollection() {
        bug.addComment(VALID_COMMENT);
        assertEquals(VALID_COMMENT, bug.getComments().get(0));
    }

    @Test
    public void getChanges_Should_ReturnCopyOfTheCollection() {
        // TODO: 18.11.2023 г. add activity when implemented
//        bug.getChanges().add()

        assertEquals(0, bug.getChanges().size());
    }
}