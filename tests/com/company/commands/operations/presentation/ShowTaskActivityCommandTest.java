package com.company.commands.operations.presentation;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.exceptions.ElementNotFoundException;
import com.company.models.contracts.Board;
import com.company.models.contracts.Feedback;
import com.company.models.contracts.Team;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.NamingConstraints.VALID_BOARD_NAME;
import static com.company.utils.NamingConstraints.VALID_NAME;
import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShowTaskActivityCommandTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private Command command;

    @BeforeEach
    public void before() {
        TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ShowTaskActivityCommand(repository);
        Team team = repository.createTeam(VALID_NAME);
        Board board = repository.createBoard(VALID_BOARD_NAME, team);
        Feedback feedback = repository.createFeedback(VALID_TITLE, VALID_DESCRIPTION, VALID_RATING);

    }

    @Test
    public void execute_Should_ShowTaskActivity_When_ArgumentsAreValid() {
        List<String> params = List.of(ID_1_STR);
        assertSame(String.class, command.execute(params).getClass());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_TaskDoesNotExist() {
        List<String> params = List.of(ID_2_STR);
        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

}