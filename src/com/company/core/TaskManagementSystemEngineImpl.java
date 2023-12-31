package com.company.core;

import com.company.commands.contracts.Command;
import com.company.core.contracts.CommandFactory;
import com.company.core.contracts.TaskManagementSystemEngine;
import com.company.core.contracts.TaskManagementSystemRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskManagementSystemEngineImpl implements TaskManagementSystemEngine {

    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND_ERROR = "Command cannot be empty.";
    private static final String REPORT_SEPARATOR = "####################";

    private final CommandFactory commandFactory;
    private final TaskManagementSystemRepository taskManagementSystemRepository;

    public TaskManagementSystemEngineImpl() {
        this.commandFactory = new CommandFactoryImpl();
        this.taskManagementSystemRepository = new TaskManagementSystemRepositoryImpl();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    print(EMPTY_COMMAND_ERROR);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    print(ex.getMessage());
                } else {
                    print(ex.toString());
                }
            }
        }
    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        List<String> parameters = extractCommandParameters(inputLine);
        Command command = commandFactory.createCommandFromCommandName(commandName, taskManagementSystemRepository);
        String executionResult = command.execute(parameters);
        print(executionResult);
    }

    private String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
    }

    private List<String> extractCommandParameters(String inputLine) {
        return Arrays.stream(inputLine.split("\\s+(?![^{]*}})"))
                .skip(1)
                .map(e -> e.replaceAll("[{}]", ""))
                .collect(Collectors.toList());
    }

    private void print(String result) {
        System.out.println(result);
        System.out.println(REPORT_SEPARATOR);
    }
}
