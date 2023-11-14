package com.company.models;

import com.company.models.contracts.Task;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Status;
import com.company.utils.TaskConstraints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BugImplTests {
    // TODO: 14.11.2023 г. initialize user in user tests
    private final int ID = 1;
    private final User ASSIGNEE = new UserImpl(ID, "Gosho");
    private final Priority PRIORITY = Priority.HIGH;
    private final Severity SEVERITY = Severity.CRITICAL;

    private Task bug;

    @BeforeEach
    public void setUp() {
        bug = new BugImpl(ID, TaskConstraints.VALID_TITLE, TaskConstraints.VALID_DESCRIPTION,
                ASSIGNEE, PRIORITY, SEVERITY);
    }


    @Test
    public void constructor_Should_InitializeTitle_When_ArgumentsAreValid() {
        Assertions.assertEquals(TaskConstraints.VALID_TITLE, bug.getTitle());
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BugImpl(ID, TaskConstraints.INVALID_TITLE,
                TaskConstraints.VALID_DESCRIPTION, ASSIGNEE, PRIORITY, SEVERITY));
    }

    @Test
    public void constructor_Should_InitializeDescription_When_ArgumentsAreValid() {
        Assertions.assertEquals(TaskConstraints.VALID_DESCRIPTION, bug.getDescription());
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BugImpl(ID, TaskConstraints.VALID_TITLE,
                TaskConstraints.INVALID_DESCRIPTION, ASSIGNEE, PRIORITY, SEVERITY));
    }

    @Test
    public void status_Should_AdvanceToDone_When_StatusAtActive() {
        bug.advanceStatus();
        Assertions.assertEquals(Status.DONE, bug.getStatus());
    }

    @Test
    public void status_Should_ThrowException_When_StatusAlreadyAtDone() {
        bug.advanceStatus();
        Assertions.assertThrows(IllegalArgumentException.class, () -> bug.advanceStatus());
    }

    @Test
    public void status_Should_RevertToActive_When_StatusAtDone() {
        bug.advanceStatus();
        bug.revertStatus();
        Assertions.assertEquals(Status.ACTIVE, bug.getStatus());
    }

    @Test
    public void status_Should_ThrowException_When_StatusAlreadyAtActive() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> bug.revertStatus());
    }
}