package com.company.commands;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.exceptions.ElementNotFoundException;
import com.company.models.UserImpl;
import com.company.models.contracts.User;
import com.company.utils.TaskBaseConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateBugCommandTests {

    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    // TODO: 18.11.2023 г. initialize user in user tests
    private static final User ASSIGNEE = new UserImpl(1, "Gosho");
    private static final String ASSIGNEE_STR = "Gosho";
    private static final String PRIORITY = "high";
    private static final String SEVERITY = "critical";
    private static final String STEPS = TestUtilities.getString(5);
    private static final String INVALID_ENUM = "INVALID_ENUM";

    private Command command;
    private TaskManagementSystemRepository repository;

    @BeforeEach
    public void before() {
        this.repository = new TaskManagementSystemRepositoryImpl();
        this.command = new CreateBugCommand(repository);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_AssigneeDoesNotExist() {
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION, ASSIGNEE_STR, PRIORITY, SEVERITY, STEPS);
        assertThrows(ElementNotFoundException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_PriorityNotValid() {
        // TODO: 18.11.2023 г. add user to repo when implemented
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION, ASSIGNEE_STR, INVALID_ENUM, SEVERITY, STEPS);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @Test
    public void execute_Should_ThrowException_When_SeverityNotValid() {
        // TODO: 18.11.2023 г. add user to repo when implemented
        List<String> params = List.of(TaskBaseConstraints.VALID_TITLE, TaskBaseConstraints.VALID_DESCRIPTION, ASSIGNEE_STR,PRIORITY, INVALID_ENUM, STEPS);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }
}
// title = parameters.get(0);
//        description = parameters.get(1);
//        assignee = taskManagementSystemRepository.findUserByName(parameters.get(2));
//        priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
//        severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class);
//        steps = Arrays.stream(parameters.get(5).split("\\d. ")).collect(Collectors.toList());