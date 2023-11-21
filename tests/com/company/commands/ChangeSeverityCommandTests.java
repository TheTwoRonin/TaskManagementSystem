package com.company.commands;

import com.company.commands.contracts.Command;
import com.company.commands.operations.modification.ChangeSeverityCommand;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Bug;
import com.company.models.enums.Severity;
import com.company.utils.TaskBaseConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.CommandConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeSeverityCommandTests {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ChangeSeverityCommand(repository);
        this.repository.createBug(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                TaskBaseConstraints.VALID_ASSIGNEE, TaskBaseConstraints.VALID_PRIORITY,
                TaskBaseConstraints.VALID_SEVERITY, TaskBaseConstraints.VALID_STEPS);
    }

    @Test
    public void execute_Should_ChangeSeverity_When_ArgumentsAreValid() {
        List<String> params = List.of(VALID_NUM_STR, VALID_SEVERITY_STR);
        command.execute(params);
        assertEquals(Severity.MINOR,((Bug) repository.findTaskById(TaskBaseConstraints.VALID_ID)).getSeverity());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_SeverityNotValid() {
        List<String> params = List.of(VALID_NUM_STR, INVALID_ENUM);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}
