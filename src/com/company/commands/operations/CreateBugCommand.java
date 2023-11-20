package com.company.commands.operations;

import com.company.commands.constants.CommandConstants;
import com.company.commands.contracts.Command;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Bug;
import com.company.models.contracts.User;
import com.company.models.enums.Priority;
import com.company.models.enums.Severity;
import com.company.utils.ParsingHelpers;
import com.company.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.company.commands.constants.CommandConstants.BUG;

public class CreateBugCommand implements Command {


    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;

    private final TaskManagementSystemRepository taskManagementSystemRepository;

    private String title;
    private String description;
    private User assignee;
    private Priority priority;
    private Severity severity;
    private List<String> steps;

    public CreateBugCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
        this.taskManagementSystemRepository = taskManagementSystemRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        parseParameters(parameters);

        Bug createdBug = taskManagementSystemRepository.createBug(title, description, assignee, priority,
                severity, steps);
        // TODO: 19.11.2023 г. add task to user
//        assignee.addTask(createdBug);
// TODO: 20.11.2023 г. add task to board

        return String.format(CommandConstants.TASK_CREATED_MESSAGE, BUG, createdBug.getId());
    }

    private void parseParameters(List<String> parameters) {
        title = parameters.get(0);
        description = parameters.get(1);
        assignee = taskManagementSystemRepository.findUserByName(parameters.get(2));
        priority = ParsingHelpers.tryParseEnum(parameters.get(3), Priority.class);
        severity = ParsingHelpers.tryParseEnum(parameters.get(4), Severity.class);
        steps = Arrays.stream(parameters.get(5).split("\\d. ")).collect(Collectors.toList());
    }

}
