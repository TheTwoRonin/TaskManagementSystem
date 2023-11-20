package com.company.commands;

import com.company.commands.contracts.Command;
import com.company.commands.operations.creation.CreateBoardCommand;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.NamingConstraints.VALID_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateBoardCommandTests {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;

    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreateBoardCommand(repository);
        repository.createTeam(VALID_NAME);
    }

    @Test
    public void execute_Should_CreateBoard_When_ArgumentsAreValid(){
        List<String> params = List.of(VALID_NAME, VALID_NAME);

        command.execute(params);

        assertEquals(VALID_NAME, repository.findTeamByName(VALID_NAME).getBoards().get(0).getName());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_BoardIsUnique_is_false() {
        List<String> params = List.of(VALID_NAME, VALID_NAME);

        command.execute(params);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}
