package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Bug;
import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.company.utils.NamingConstraints.VALID_NAME;
import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.assertSame;

class ListSortedTaskAssignmentsByTitleCommandTest {
    private Command command;

    @BeforeEach
    public void before() {
        TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ListSortedTaskAssignmentsByTitleCommand(repository);
        User user = repository.createUser(VALID_NAME);
        Story story = repository.createStory("B" + VALID_TITLE, VALID_DESCRIPTION, user, VALID_PRIORITY, VALID_SIZE);
        Bug bug = repository.createBug("A" + VALID_TITLE, VALID_DESCRIPTION, user, VALID_PRIORITY, VALID_SEVERITY, VALID_STEPS);
    }

    @Test
    public void execute_Should_ListSortedTaskAssignments() {
        List<String> params = List.of(VALID_TITLE);
        assertSame(String.class, command.execute(params).getClass());
    }


}