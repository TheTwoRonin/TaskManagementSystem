package com.company.models;

import com.company.models.contracts.Bug;
import com.company.models.enums.Status;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.company.utils.TaskBaseConstraints.*;

public class BugImplTests {
    private Bug bug;

    @BeforeEach
    public void setUp() {
        bug = new BugImpl(VALID_ID, VALID_TITLE, VALID_DESCRIPTION, VALID_ASSIGNEE,
                VALID_PRIORITY, VALID_SEVERITY, VALID_STEPS);
    }

    @Test
    public void constructor_Should_InitializeTitle_When_ArgumentsAreValid() {
        Assertions.assertEquals(VALID_TITLE, bug.getTitle());
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BugImpl(VALID_ID, INVALID_TITLE,
                VALID_DESCRIPTION, VALID_ASSIGNEE, VALID_PRIORITY,
                VALID_SEVERITY, VALID_STEPS));
    }

    @Test
    public void constructor_Should_InitializeDescription_When_ArgumentsAreValid() {
        Assertions.assertEquals(VALID_DESCRIPTION, bug.getDescription());
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BugImpl(VALID_ID, VALID_TITLE,
                INVALID_DESCRIPTION, VALID_ASSIGNEE, VALID_PRIORITY,
                VALID_SEVERITY, VALID_STEPS));
    }

    @Test
    public void status_Should_ChangeStatus_When_ValidStatus() {
        bug.changeStatus(Status.DONE);
        Assertions.assertEquals(Status.DONE, bug.getStatus());
    }

    @Test
    public void status_Should_ThrowException_When_InvalidStatus() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bug.changeStatus(Status.UNSCHEDULED));
    }

    @Test
    public void getSteps_Should_ReturnCopyOfTheCollection() {
        bug.getSteps().add(TestUtilities.getString(5));

        Assertions.assertEquals(0, bug.getSteps().size());
    }


    @Test
    public void getComments_Should_ReturnCopyOfTheCollection() {
        bug.getComments().add(VALID_COMMENT);

        Assertions.assertEquals(0, bug.getComments().size());
    }

    @Test
    public void getChanges_Should_ReturnCopyOfTheCollection() {
        // TODO: 18.11.2023 г. add activity when implemented
//        bug.getChanges().add()

        Assertions.assertEquals(0, bug.getChanges().size());
    }
}