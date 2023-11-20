package com.company.commands;

import com.company.commands.contracts.Command;
import com.company.commands.operations.creation.CreateBugCommand;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.exceptions.ElementNotFoundException;
import com.company.utils.TaskBaseConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.CommandConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateBugCommandTests {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;


    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreateBugCommand(repository);
        repository.createUser(VALID_ASSIGNEE_STR);
    }

    @Test
    public void execute_Should_CreateBug_When_ArgumentsAreValid() {
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                VALID_ASSIGNEE_STR, VALID_PRIORITY_STR, VALID_SEVERITY_STR, VALID_STEPS);
        command.execute(params);
        assertEquals(1, repository.getTasks().size());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_AssigneeDoesNotExist() {
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                INVALID_ASSIGNEE_STR, VALID_PRIORITY_STR, VALID_SEVERITY_STR, VALID_STEPS);
        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_PriorityNotValid() {
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                VALID_ASSIGNEE_STR, INVALID_ENUM, VALID_SEVERITY_STR, VALID_STEPS);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_SeverityNotValid() {
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                VALID_ASSIGNEE_STR, VALID_PRIORITY_STR, INVALID_ENUM, VALID_STEPS);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}
