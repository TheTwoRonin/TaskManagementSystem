package com.company.commands.operations.modification;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.enums.Priority;
import com.company.utils.TaskBaseConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.CommandConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangePriorityCommandTests {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ChangePriorityCommand(repository);
        this.repository.createBug(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                TaskBaseConstraints.VALID_ASSIGNEE, TaskBaseConstraints.VALID_PRIORITY,
                TaskBaseConstraints.VALID_SEVERITY, TaskBaseConstraints.VALID_STEPS);
    }

    @Test
    public void execute_Should_ChangePriority_When_ArgumentsAreValid() {
        List<String> params = List.of(VALID_NUM_STR, VALID_PRIORITY_STR);
        command.execute(params);
        assertEquals(Priority.LOW, repository.findTaskAssignmentById(TaskBaseConstraints.VALID_ID).getPriority());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_PriorityNotValid() {
        List<String> params = List.of(VALID_NUM_STR, INVALID_ENUM);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}
