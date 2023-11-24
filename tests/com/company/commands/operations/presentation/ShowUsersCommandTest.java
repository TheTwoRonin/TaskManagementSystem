package com.company.commands.operations.presentation;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.NamingConstraints.VALID_NAME;
import static org.junit.jupiter.api.Assertions.assertSame;

class ShowUsersCommandTest {
    private Command command;

    @BeforeEach
    public void before() {
        TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ShowUsersCommand(repository);
        User user = repository.createUser(VALID_NAME);
    }

    @Test
    public void execute_Should_Show_Users_When_ArgumentsAreValid() {
        List<String> params = List.of(VALID_NAME);
        assertSame(String.class, command.execute(params).getClass());
    }
}