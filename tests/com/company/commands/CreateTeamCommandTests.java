package com.company.commands;

import com.company.commands.contracts.Command;
import com.company.commands.operations.creation.CreateTeamCommand;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.NamingConstraints.VALID_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateTeamCommandTests {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;

    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreateTeamCommand(repository);
    }

    @Test
    public void execute_Should_CreateTeam_When_ArgumentsAreValid(){
        List<String> params = List.of(VALID_NAME);

        command.execute(params);

        assertEquals(VALID_NAME, repository.getTeams().get(0).getName());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_TeamIsUnique_is_false() {
        List<String> params = List.of(VALID_NAME);

        command.execute(params);

        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}
