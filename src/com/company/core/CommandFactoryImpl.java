package com.company.core;

import com.company.commands.contracts.Command;
import com.company.commands.enums.CommandType;
import com.company.commands.listing.ListFilteredBugsCommand;
import com.company.commands.listing.ListFilteredTasksCommand;
import com.company.commands.listing.ListSortedBugsByFieldCommand;
import com.company.commands.listing.ListSortedTasksCommand;
import com.company.commands.operations.creation.*;
import com.company.commands.operations.modification.*;
import com.company.commands.operations.presentation.*;
import com.company.core.contracts.CommandFactory;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.utils.ParsingHelpers;

public class CommandFactoryImpl implements CommandFactory {

    @Override
    public Command createCommandFromCommandName(String commandTypeAsString, TaskManagementSystemRepository taskManagementSystemRepository) {
        CommandType commandType = ParsingHelpers.tryParseEnum(commandTypeAsString, CommandType.class);
        return switch (commandType) {
            case ADDCOMMENT -> new AddCommentCommand(taskManagementSystemRepository);
            case ADDUSERTOTEAM -> new AddUserToTeamCommand(taskManagementSystemRepository);
            case ASSIGNTASKTOUSER -> new AssignTaskToUserCommand(taskManagementSystemRepository);
            case CHANGEPRIORITY -> new ChangePriorityCommand(taskManagementSystemRepository);
            case CHANGERATING -> new ChangeRatingCommand(taskManagementSystemRepository);
            case CHANGESEVERITY -> new ChangeSeverityCommand(taskManagementSystemRepository);
            case CHANGESIZE -> new ChangeSizeCommand(taskManagementSystemRepository);
            case CHANGESTATUS -> new ChangeStatusCommand(taskManagementSystemRepository);
            case CREATEBOARD -> new CreateBoardCommand(taskManagementSystemRepository);
            case CREATEBUG -> new CreateBugCommand(taskManagementSystemRepository);
            case CREATEFEEDBACK -> new CreateFeedbackCommand(taskManagementSystemRepository);
            case CREATEUSER -> new CreateUserCommand(taskManagementSystemRepository);
            case CREATESTORY -> new CreateStoryCommand(taskManagementSystemRepository);
            case CREATETEAM -> new CreateTeamCommand(taskManagementSystemRepository);
            case SHOWUSERS -> new ShowUsersCommand(taskManagementSystemRepository);
            case SHOWTEAMBOARDS -> new ShowTeamBoardsCommand(taskManagementSystemRepository);
            case SHOWTEAMMEMBERS -> new ShowTeamMembersCommand(taskManagementSystemRepository);
            case SHOWTEAMS -> new ShowTeamsCommand(taskManagementSystemRepository);
            case SHOWBOARDACTIVITY -> new ShowBoardActivityCommand(taskManagementSystemRepository);
            case SHOWUSERACTIVITY -> new ShowUserActivityCommand(taskManagementSystemRepository);
            case SHOWTEAMACTIVITY -> new ShowTeamActivityCommand(taskManagementSystemRepository);
            case UNASSIGNTASKFROMUSER -> new UnassignTaskFromUserCommand(taskManagementSystemRepository);
            case LISTSORTEDTASKS -> new ListSortedTasksCommand(taskManagementSystemRepository);
            case LISTFILTEREDTASKS -> new ListFilteredTasksCommand(taskManagementSystemRepository);
            case LISTSORTEDBUGSBYFIELD -> new ListSortedBugsByFieldCommand(taskManagementSystemRepository);
            case LISTFILTEREDBUGS -> new ListFilteredBugsCommand(taskManagementSystemRepository);
            case LISTFILTEREDFEEDBACKS -> new ListFilteredBugsCommand(taskManagementSystemRepository);
            case LISTFILTEREDSTORIES -> new ListFilteredBugsCommand(taskManagementSystemRepository);

            default -> throw new IllegalArgumentException("nqma takaa komanda");
        };
    }
}
