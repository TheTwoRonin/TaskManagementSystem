package com.company.commands.operations.creation;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.TaskBaseConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.CommandConstraints.*;
import static com.company.utils.NamingConstraints.VALID_BOARD_NAME;
import static com.company.utils.NamingConstraints.VALID_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateStoryCommandTests {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreateStoryCommand(repository);
        repository.createUser(VALID_ASSIGNEE_STR);
        repository.createBoard(VALID_BOARD_NAME, repository.createTeam(VALID_NAME));
    }

    @Test
    public void execute_Should_CreateStory_When_ArgumentsAreValid() {
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                VALID_ASSIGNEE_STR, VALID_PRIORITY_STR, VALID_SIZE_STR, VALID_BOARD_NAME);
        command.execute(params);
        assertEquals(1, repository.getTasks().size());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_SizeNotValid() {
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                VALID_ASSIGNEE_STR, VALID_PRIORITY_STR, INVALID_ENUM, VALID_BOARD_NAME);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}
