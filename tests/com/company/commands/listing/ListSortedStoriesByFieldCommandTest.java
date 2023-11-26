package com.company.commands.listing;

import com.company.commands.contracts.Command;
import com.company.core.TaskManagementSystemRepositoryImpl;
import com.company.core.contracts.TaskManagementSystemRepository;
import com.company.models.contracts.Story;
import com.company.models.contracts.User;
import com.company.utils.CommandConstraints;
import com.company.utils.TestUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.company.utils.NamingConstraints.VALID_NAME;
import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.*;

class ListSortedStoriesByFieldCommandTest {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 1;
    private Command command;

    private static Stream<String[]> sorterFilterProvider() {
        return CommandConstraints.STORY_FILTER_LIST.stream().map(i -> new String[]{i, i});

    }


    @BeforeEach
    public void before() {
        TaskManagementSystemRepository repository = new TaskManagementSystemRepositoryImpl();
        this.command = new ListSortedStoriesByFieldCommand(repository);
        User user1 = repository.createUser(VALID_NAME);
        User user2 = repository.createUser("A" + VALID_NAME);
        Story story = repository.createStory("B" + VALID_TITLE, "A" + VALID_DESCRIPTION, user1, VALID_PRIORITY, VALID_SIZE);
        Story story2 = repository.createStory("A" + VALID_TITLE, "B" + VALID_DESCRIPTION, user2, VALID_PRIORITY, VALID_SIZE);
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
        List<String> params = List.of(CommandConstraints.STORY_FILTER_LIST.get(0));
        assertNotEquals(command.execute(List.of(CommandConstraints.STORY_FILTER_LIST.get(1))), command.execute(params));
    }

}