package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Bug;
import com.company.models.contracts.Feedback;
import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.company.utils.NamingConstraints.VALID_NAME;
import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListFilteredTasksCommandTest {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private Command command;

    @BeforeEach
    public void before() {
        TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ListFilteredTasksCommand(repository);
        User user = repository.createUser(VALID_NAME);
        Story story = repository.createStory(VALID_TITLE, VALID_DESCRIPTION, user, VALID_PRIORITY, VALID_SIZE);
        Bug bug = repository.createBug(VALID_TITLE, VALID_DESCRIPTION, user, VALID_PRIORITY, VALID_SEVERITY, VALID_STEPS);
        Feedback feedback = repository.createFeedback(VALID_TITLE, VALID_DESCRIPTION, VALID_RATING);
    }

    @Test
    public void execute_Should_ListFilteredTaskAssignments_When_ArgumentIsValid() {
        List<String> params = List.of(VALID_TITLE);
        assertSame(String.class, command.execute(params).getClass());
    }


    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

}