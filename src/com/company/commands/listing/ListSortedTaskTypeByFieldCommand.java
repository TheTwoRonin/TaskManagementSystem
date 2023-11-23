//package com.company.commands.listing;
//
//import com.company.commands.contracts.Command;
//import com.company.core.contracts.TaskManagementSystemRepository;
//import com.company.models.contracts.Bug;
//import com.company.models.contracts.IntermediateTask;
//import com.company.models.contracts.Task;
//import com.company.utils.ValidationHelpers;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//import static com.company.utils.ListingHelpers.listSortedTasks;
//
//public class ListSortedTaskTypeByFieldCommand implements Command {
//
//    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
//
//    private String taskType;
//    private String sortType;
//
//    private final TaskManagementSystemRepository taskManagementSystemRepository;
//
//    public ListSortedTaskTypeByFieldCommand(TaskManagementSystemRepository taskManagementSystemRepository) {
//        this.taskManagementSystemRepository = taskManagementSystemRepository;
//    }
//
//    @Override
//    public String execute(List<String> parameters) {
//
//        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
//
//        parseParameters(parameters);
//
//        List<T extends IntermediateTask> listOfType = taskManagementSystemRepository.getBugs();
//
//        sortTypeFunction = "s";
//
//        Function<Task, String> getTitle = Task::getTitle;
//        listSortedTasks(listOfType, getTitle);
//        return taskManagementSystemRepository
//                .getTasks()
//                .stream()
//                .filter(task -> task.getClass().getSimpleName().equals("%sImpl".formatted(taskType)))
//                .sorted(Comparator.comparing(getTitle))
//                .map(Object::toString)
//                .collect(Collectors.joining("\n-------\n"));
//    }
//
//    private void parseParameters(List<String> parameters) {
//        taskType = parameters.get(0);
//        sortType = parameters.get(1);
//        //title, priority, severity, size/rating
//    }
//
//}
