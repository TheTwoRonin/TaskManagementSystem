package com.company.commands;

import com.company.commands.contracts.Command;
import com.company.commands.operations.creation.CreateFeedbackCommand;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Board;
import com.company.models.contracts.Team;
import com.company.utils.CommandConstraints;
import com.company.utils.TaskBaseConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.CommandConstraints.VALID_NUM_STR;
import static com.company.utils.NamingConstraints.VALID_BOARD_NAME;
import static com.company.utils.NamingConstraints.VALID_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateFeedbackCommandTests {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreateFeedbackCommand(repository);
        Team team = repository.createTeam(VALID_NAME);
        Board board = repository.createBoard(VALID_BOARD_NAME, team);
    }

    @Test
    public void execute_Should_CreateFeedback_When_ArgumentsAreValid() {
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                VALID_NUM_STR, VALID_BOARD_NAME);
        command.execute(params);
        assertEquals(1, repository.getTasks().size());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_RatingNonNumber() {
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                CommandConstraints.INVALID_NUM_STR);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}
