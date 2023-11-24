package com.company.models;

import com.company.utils.CommandConstraints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActivityTests {

    @Test
    public void should_ThrowException_When_InvalidDescription() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Activity(CommandConstraints.INVALID_DESCRIPTION_STR));
    }
}
