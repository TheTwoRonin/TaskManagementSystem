package com.company.commands.operations.presentation;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.exceptions.ElementNotFoundException;
import com.company.models.contracts.Board;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.NamingConstraints.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ShowTeamBoardsCommandTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private Command command;

    @BeforeEach
    public void before() {
        TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ShowTeamBoardsCommand(repository);
        Board board = repository.createBoard(VALID_BOARD_NAME, repository.createTeam(VALID_NAME));

    }

    @Test
    public void execute_Should_ShowTeamBoards_When_ArgumentsAreValid() {
        List<String> params = List.of(VALID_NAME);
        assertSame(String.class, command.execute(params).getClass());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_TeamDoesNotExist() {
        List<String> params = List.of(VALID_NAME2);
        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

}