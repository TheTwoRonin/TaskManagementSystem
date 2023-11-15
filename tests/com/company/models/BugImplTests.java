package com.company.models;

import com.company.models.contracts.Comment;
import com.company.models.contracts.Task;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.models.enums.Status;
import com.company.utils.TaskConstraints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BugImplTests {
    // TODO: 14.11.2023 г. initialize user in user tests
    private final int ID = 1;
    private final List<Comment> COMMENTS = new ArrayList<>();
    private final User ASSIGNEE = new UserImpl(ID, "Gosho");
    private final Priority PRIORITY = Priority.HIGH;
    private final Severity SEVERITY = Severity.CRITICAL;

    private Task bug;

    @BeforeEach
    public void setUp() {
        bug = new BugImpl(ID, TaskConstraints.VALID_TITLE, TaskConstraints.VALID_DESCRIPTION, COMMENTS,
                ASSIGNEE, PRIORITY, SEVERITY);
    }


    @Test
    public void constructor_Should_InitializeTitle_When_ArgumentsAreValid() {
        Assertions.assertEquals(TaskConstraints.VALID_TITLE, bug.getTitle());
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BugImpl(ID, TaskConstraints.INVALID_TITLE,
                TaskConstraints.VALID_DESCRIPTION, COMMENTS, ASSIGNEE, PRIORITY, SEVERITY));
    }

    @Test
    public void constructor_Should_InitializeDescription_When_ArgumentsAreValid() {
        Assertions.assertEquals(TaskConstraints.VALID_DESCRIPTION, bug.getDescription());
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new BugImpl(ID, TaskConstraints.VALID_TITLE,
                TaskConstraints.INVALID_DESCRIPTION, COMMENTS, ASSIGNEE, PRIORITY, SEVERITY));
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
}