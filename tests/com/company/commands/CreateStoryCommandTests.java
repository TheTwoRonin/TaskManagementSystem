package com.company.commands;

import com.company.commands.contracts.Command;
import com.company.commands.operations.CreateStoryCommand;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateStoryCommandTests {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;

    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreateStoryCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

//    @Test
//    public void execute_Should_ThrowException_When_SizeNotValid() {
//        // TODO: 19.11.2023 г. add user to repo when implemented
//        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
//                VALID_ASSIGNEE_STR, VALID_PRIORITY_STR, INVALID_ENUM);
//        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
//    }
}
