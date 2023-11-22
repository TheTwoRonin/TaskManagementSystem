package com.company.commands.operations.modification;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.TaskBaseConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.CommandConstraints.INVALID_NUM_STR;
import static com.company.utils.CommandConstraints.VALID_NUM_STR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ChangeRatingCommandTests {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ChangeRatingCommand(repository);
        this.repository.createFeedback(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION,
                TaskBaseConstraints.VALID_RATING);
    }

    @Test
    public void execute_Should_ChangeRating_When_ArgumentsAreValid() {
        List<String> params = List.of(VALID_NUM_STR, VALID_NUM_STR);
        command.execute(params);
        assertEquals(1, repository.findFeedbackById(TaskBaseConstraints.VALID_ID).getRating());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void should_ThrowException_When_RatingNonNum() {
        List<String> params = List.of(VALID_NUM_STR, INVALID_NUM_STR);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}