package com.company.commands.operations.modification;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.exceptions.ElementNotFoundException;
import com.company.utils.TaskBaseConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.CommandConstraints.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommentTests {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;


    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new AddCommentCommand(repository);
    }

    @Test
    public void execute_Should_AddComment_When_ArgumentsAreValid() {
        this.repository.createFeedback(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                TaskBaseConstraints.VALID_RATING);
        List<String> params = List.of(VALID_NUM_STR, VALID_CONTENT, VALID_AUTHOR);
        command.execute(params);
        assertEquals(1, repository.findTaskById(1).getComments().size());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_IdNonNumber() {
        List<String> params = List.of(INVALID_NUM_STR, VALID_CONTENT, VALID_AUTHOR);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_TaskDoesNotExist() {
        List<String> params = List.of(VALID_NUM_STR, VALID_CONTENT, VALID_AUTHOR);
        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }
}
