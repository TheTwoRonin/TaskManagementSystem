package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Feedback;
import com.company.utils.CommandConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.*;

class ListSortedFeedbackByFieldCommandTest {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private Command command;

    private static Stream<String[]> sorterFilterProvider() {
        return CommandConstraints.FEEDBACK_FILTER_LIST.stream().map(i -> new String[]{i, i});

    }

    @BeforeEach
    public void before() {
        TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ListSortedFeedbackByFieldCommand(repository);
        Feedback feedback = repository.createFeedback("B" + VALID_TITLE, "A" + VALID_DESCRIPTION, VALID_RATING);
        Feedback feedback2 = repository.createFeedback("A" + VALID_TITLE, "B" + VALID_DESCRIPTION, VALID_RATING);
    }

    @Test
    public void execute_Should_ListSortedBugs_When_ArgumentIsValid() {
        List<String> params = List.of(CommandConstraints.TITLE_STRING);
        assertSame(String.class, command.execute(params).getClass());
    }


    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        List<String> params = TestUtilities.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);
        assertThrows(IllegalArgumentException.class, () -> command.execute(params));
    }

    @ParameterizedTest
    @MethodSource("sorterFilterProvider")
    public void getSortField_Should_SortBySorterFilterCorrect(String first, String second) {
        List<String> params = List.of(first);
        assertEquals(command.execute(List.of(second)), command.execute(params));
    }

    @Test
    public void getSortField_Should_NotBeEqualsWhenSortedByDifferentSorters() {
        List<String> params = List.of(CommandConstraints.FEEDBACK_FILTER_LIST.get(0));
        assertNotEquals(command.execute(List.of(CommandConstraints.FEEDBACK_FILTER_LIST.get(1))), command.execute(params));
    }

}