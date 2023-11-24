# TaskManagement System

## Description

This application is intended to be used by a small team of developers, who need to keep track of all the tasks,
surrounding a software product they are building.

## Commands

Currently, the engine supports the following commands:

#### Creation:

- **CreateUser** `[username] ` - registers a user. Username should be between 5 and 15 symbols.
- **CreateTeam** `[name]` - registers a team. Username should be between 5 and 15 symbols.
- **CreateBoard**  `[name]` `[teamName]` - creates a board, requires a team. Name should be between 5 and 10 symbols.
- **CreateFeedback** `[title] [description] [rating]` `[boardName]` - creates a feedback, requires a board. name and
  description should be between 10 and 100/500 symbols. Use brackets like this to concatenate long titles and
  descriptions {{Long title example}}
- **
  CreateBug** `[title] [description]` `[steps]` `[priority]` `[teamName]` `[severity]` `[status]` `[userName]` `[boardName]`
  - creates a bug, requires a user and a board, name and description should be between 10 and 100/500 symbols. {{Long
    title example}} applicable.
- **CreateStory** `[title] [description]` `[priority]` `[size]` `[status]` `[userName]` `[boardName]`  - creates a
  story, requires a user and a board, name and description should be between 10 and 100/500 symbols. {{Long title
  example}} applicable.

#### Modification:

- **AddComment** `[taskId]` `[comment]` `[author/userName]` - adds a comment to a task.
- **AddUserToTeam** `[userName]` `[teamName]` - adds a user to a team.
- **AssignTaskToUser** `[taskId]` `[userName]` - Assigns a task to a user.
- **UnassignTaskToUser** `[taskId]` `[userName]` - Unassigns a task from a user.
- **ChangePriority** `[taskId]` `[newPriority]` - Changes the priority of a Bug/Story.
- **ChangeRating** `[taskId]` `[newRating]` - Changes the rating of a feedback.
- **ChangeSeverity** `[taskId]` `[newPriority]` - Changes the severity of a Bug.
- **ChangeSeverity** `[taskId]` `[newSize]` - Changes the severity of a Story.
- **ChangeSeverity** `[taskId]` `[newPriority]` - Changes the status of a Bug/Story/Feedback.

#### Show/List:

- **ShowUsers**  - Prints info on all users in the system.
- **ShowTeamBoards** `[teamName]` - Prints info for all boards that belong to the team.
- **ShowTeamMembers** `[teamName]` - Prints info for all members that belong to the team.
- **ShowTeams**  - Prints info on all teams in the system.
- **ShowBoardActivity** `[boardName]` - Prints info for all activities related to that board.
- **ShowTaskActivity** `[taskId]` - Prints info for all activities related to that task.
- **ShowUserActivity** `[userName]` - Prints info for all activities related to that user.
- **ShowTeamMembers** `[teamName]` - Prints info for all activities related to that team.
- **ListSortedTasks**  - Prints info on all tasks in the system, sorted by title.
- **ListFilteredTasks** `[filter]` - Prints info on all tasks in the system, filtered by title.
- **ListFilteredTasksAssignments** `[filter]`  - Prints info on all tasks that have an assignee in the system, filtered
  by title and/or assignee.
- **ListSortedBugsByField** `[sorter]` - Prints info on all bugs logged in the system, sorted by
  title/description/assignee/status/priority/severity.
- **ListSortedStoriesByField**  `[sorter]` - Prints info on all stories logged in the system, sorted by
  title/description/assignee/status/priority/size.
- **ListSortedFeedbackByField** `[sorter]` - Prints info on all feedback logged in the system, sorted by
  title/description/rating.
- **ListSortedTasks**  - Prints info on all tasks that have an assignee in the system, sorted by title.
- **ListFilteredBugsAssignments** `[filter]` - Prints info on all bugs in the system, filtered by title and/or assignee.
- **ListFilteredBugs** `[filter]` - Prints info on all feedback in the system, filtered by title and/or assignee.
- **ListFilteredStories** `[filter]` - Prints info on all bugs in the system, filtered by title and/or assignee.

## Use case scenarios

### Use case # 1

One of the developers has noticed a bug in the company's product. He starts the
application and goes on to create a new Task for it. He creates a new Bug and gives
it the title "The program freezes when the Log In button is clicked." For the
description he adds "This needs to be fixed quickly!", he marks the Bug as High
priority and gives it Critical severity. Since it is a new bug, it gets the Active status.
The developer also assigns it to the senior developer in the team. To be able to fix the
bug, the senior developer needs to know how to reproduce it, so the developer who
logged the bug adds a list of steps to reproduce: "1. Open the application; 2. Click
"Log In"; 3. The application freezes!" The bug is saved to the application and is ready
to be fixed.

### Example inputs:

```
createuser MainCharacter
createuser SeniorDev
createteam TheTwoRonin
addusertoteam MainCharacter TheTwoRonin
addusertoteam SeniorDev TheTwoRonin
createboard MainApp TheTwoRonin
createbug {{The program freezes when the Log In button is clicked.}} {{This needs to be fixed quickly!}} SeniorDev High Critical {{1. Open the application; 2. Click "Log In"; 3. The application freezes!}} MainApp
showteamboards TheTwoRonin
```

### Expected output
```

User with name MainCharacter was created.
####################
User with name SeniorDev was created.
####################
Team with name TheTwoRonin was created.
####################
User MainCharacter added to team TheTwoRonin successfully.
####################
User SeniorDev added to team TheTwoRonin successfully.
####################
Board with name MainApp was created.
####################
Bug with ID 1 was created.
####################


Board name: MainApp
Tasks:
Bug #ID- 1
Title: "The program freezes when the Log In button is clicked."
Description: "This needs to be fixed quickly!"
Status: Active
Comments:
No entries.
Assignee: SeniorDev
Priority: High
Severity: Critical
1. Open the application; 
------
2. Click "Log In"; 
------
3. The application freezes!

####################
```

### Use case # 2

A new developer has joined the team. One of the other developers starts the
application and creates a new team member. After that, he adds the new team
member to one of the existing teams and assigns all Critical bugs to him.

### Example inputs:

```
createuser SeniorDev
createuser NewJuniorDev
createteam TheTwoRonin
addusertoteam SeniorDev TheTwoRonin
addusertoteam NewJuniorDev TheTwoRonin
createboard MainApp TheTwoRonin
createbug {{The program freezes when the Log In button is clicked.}} {{This needs to be fixed quickly!}} SeniorDev High Critical {{1. Open the application; 2. Click "Log In"; 3. The application freezes!}} MainApp
createbug {{The coffee machine got cold.}} {{Some coffees need to be brewed immediately!}} SeniorDev High Minor {{1. Start the machine; 2. Grind coffee 3. Exctract shots!}} MainApp
createbug {{We need a new MVP}} {{We have no cool features}} SeniorDev Medium Critical {{1. Sit; 2. Drink the coffee from the prev bug 3. Think!}} MainApp
createbug {{Think of new ideas for bugs}} {{Use chat GPT I guess}} SeniorDev High Major {{1. Sit; 2. Drink the coffee from bug 2. Think!}} MainApp
listsortedbugsbyfield priority
assigntasktouser 1 NewJuniorDev
assigntasktouser 2 NewJuniorDev
assigntasktouser 4 NewJuniorDev
```

### Expected output
```

User with name SeniorDev was created.
####################
User with name NewJuniorDev was created.
####################
Team with name TheTwoRonin was created.
####################
User SeniorDev added to team TheTwoRonin successfully.
####################
User NewJuniorDev added to team TheTwoRonin successfully.
####################
Board with name MainApp was created.
####################
Bug with ID 1 was created.
####################
Bug with ID 2 was created.
####################
Bug with ID 3 was created.
####################
Bug with ID 4 was created.
####################
Bug #ID- 1
Title: "The program freezes when the Log In button is clicked."
Description: "This needs to be fixed quickly!"
Status: Active
Comments:
No entries.
Assignee: SeniorDev
Priority: High
Severity: Critical
1. Open the application; 
------
2. Click "Log In"; 
------
3. The application freezes!

-------
Bug #ID- 2
Title: "The coffee machine got cold."
Description: "Some coffees need to be brewed immediately!"
Status: Active
Comments:
No entries.
Assignee: SeniorDev
Priority: High
Severity: Minor
1. Start the machine; 
------
2. Grind coffee 
------
3. Exctract shots!

-------
Bug #ID- 4
Title: "Think of new ideas for bugs"
Description: "Use chat GPT I guess"
Status: Active
Comments:
No entries.
Assignee: SeniorDev
Priority: High
Severity: Major
1. Sit; 
------
2. Drink the coffee from bug 
------
2. Think!

-------
Bug #ID- 3
Title: "We need a new MVP"
Description: "We have no cool features"
Status: Active
Comments:
No entries.
Assignee: SeniorDev
Priority: Medium
Severity: Critical
1. Sit; 
------
2. Drink the coffee from the prev bug 
------
3. Think!
####################
Task with ID 1 added to user NewJuniorDev successfully.
####################
Task with ID 2 added to user NewJuniorDev successfully.
####################
Task with ID 4 added to user NewJuniorDev successfully.
####################
```

### Use case # 3

Use case #3
One of the developers has fixed a bug that was assigned to him. He adds a comment
to that bug, saying "This one took me a while, but it is fixed now!", and then changes
the status of the bug to Done. Just to be sure, he checks the changes history list of
the bug and sees that the last entry in the list says, "The status of item with ID 42
switched from Active to Done."

### Example inputs:

```
createuser MainCharacter
createuser SeniorDev
createteam TheTwoRonin
addusertoteam MainCharacter TheTwoRonin
addusertoteam SeniorDev TheTwoRonin
createboard MainApp TheTwoRonin
createbug {{The program freezes when the Log In button is clicked.}} {{This needs to be fixed quickly!}} SeniorDev High Critical {{1. Open the application; 2. Click "Log In"; 3. The application freezes!}} MainApp
assigntasktouser 1 MainCharacter
addcomment 1 {{This one took me a while, but it is fixed now!}} MainCharacter
changestatus 1 done
showtaskactivity 1

```

### Expected output
```

User with name MainCharacter was created.
####################
User with name SeniorDev was created.
####################
Team with name TheTwoRonin was created.
####################
User MainCharacter added to team TheTwoRonin successfully.
####################
User SeniorDev added to team TheTwoRonin successfully.
####################
Board with name MainApp was created.
####################
Bug with ID 1 was created.
####################
Task with ID 1 added to user MainCharacter successfully.
####################
MainCharacter added comment successfully.
####################
Status of task with ID 1 changed successfully.
####################

[24-November-2023 12:53:08] Bug created with ID #1
------
[24-November-2023 12:53:08] Bug #1 comment: ''This one took me a while, but it is fixed now!'' added by MainCharacter 
------
[24-November-2023 12:53:08] Bug #1 Status changed from Active to Done
####################
```