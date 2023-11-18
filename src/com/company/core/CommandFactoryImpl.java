package com.company.core;

import com.company.commands.contracts.Command;
import com.company.commands.enums.CommandType;
import com.company.core.contracts.CommandFactory;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        switch (commandType) {

        }
        return null;
    }
}
