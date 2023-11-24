package com.company.commands.operations.modification;

import com.company.commands.contracts.Command;
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
import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.*;

public class UnassignTaskFromUserCommandTests {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    private Command command;
    private TaskManagementSystemRepository repository;
    private Story story;
    private User user;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new UnassignTaskFromUserCommand(repository);
        this.user = this.repository.createUser(VALID_NAME);
        this.story = this.repository.createStory(VALID_TITLE, VALID_DESCRIPTION, VALID_ASSIGNEE, VALID_PRIORITY, VALID_SIZE);
        this.user.assignTask(story);
        this.story.assignAssignee(user);
    }

    @Test
    public void execute_Should_UnassignTaskFromUser_When_ArgumentsAreValid() {
        command.execute(List.of(ID_1_STR));
        assertAll(() -> assertEquals(0, user.getTasks().size()),
                () -> assertThrows(IllegalArgumentException.class, () -> story.getAssignee()));
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_StoryDoesNotExist() {
        List<String> params = List.of(ID_2_STR);
        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }
}



