package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Bug;
import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import com.company.utils.CommandConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.NamingConstraints.VALID_NAME;
import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ListFilteredTaskAssignmentsCommandTest {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private Command command;

    @BeforeEach
    public void before() {
        TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ListFilteredTaskAssignmentsCommand(repository);
        User user = repository.createUser(VALID_NAME);
        Story story = repository.createStory(VALID_TITLE, VALID_DESCRIPTION, user, VALID_PRIORITY, VALID_SIZE);
        Bug bug = repository.createBug(VALID_TITLE, VALID_DESCRIPTION, user, VALID_PRIORITY, VALID_SEVERITY, VALID_STEPS);
    }

    @Test
    public void execute_Should_ListFilteredTaskAssignments_When_ArgumentIsValid() {
        List<String> params = List.of(CommandConstraints.VALID_STORY_STATUS_STR);
        assertSame(String.class, command.execute(params).getClass());
    }


    @Test
    public void execute_Should_ListFilteredTaskAssignments_When_ArgumentsAreValid() {
        List<String> params = List.of(CommandConstraints.VALID_STORY_STATUS_STR, VALID_NAME);
        assertSame(String.class, command.execute(params).getClass());
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

}