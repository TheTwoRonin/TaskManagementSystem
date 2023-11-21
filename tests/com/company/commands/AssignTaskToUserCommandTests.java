package com.company.commands;

import com.company.commands.contracts.Command;
import com.company.commands.operations.modification.AssignTaskToUserCommand;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.exceptions.ElementNotFoundException;
import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.NamingConstraints.VALID_NAME;
import static com.company.utils.NamingConstraints.VALID_NAME2;
import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AssignTaskToUserCommandTests {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private Command command;
    private TaskManagementSystemRepository repository;
    private Story story;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new AssignTaskToUserCommand(repository);
        User user = this.repository.createUser(VALID_NAME);
        this.story = this.repository.createStory(VALID_TITLE, VALID_DESCRIPTION, user, VALID_PRIORITY, VALID_SIZE);

    }

    @Test
    public void execute_Should_AssignTaskToUser_When_ArgumentsAreValid() {
        List<String> params = List.of(ID_1_STR, VALID_NAME);
        command.execute(params);

        assertSame(story, this.repository.findUserByName(VALID_NAME).getTasks().get(0));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_StoryDoesNotExist() {
        List<String> params = List.of(ID_2_STR, VALID_NAME);
        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_UserDoesNotExist() {
        List<String> params = List.of(ID_1_STR, VALID_NAME2);
        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }
}

