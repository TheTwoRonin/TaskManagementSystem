package com.company.models;

import com.company.models.contracts.Feedback;
import com.company.models.enums.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.utils.TaskBaseConstraints.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FeedbackTests {

    private Feedback feedback;


    @BeforeEach
    public void setUp() {
        feedback = new FeedbackImpl(VALID_ID, VALID_TITLE, VALID_DESCRIPTION, VALID_RATING);
    }

    @Test
    public void should_CreateBug_When_ArgumentsAreValid() {
        assertAll(
                () -> assertEquals(VALID_ID, feedback.getId()),
                () -> assertEquals(VALID_TITLE, feedback.getTitle()),
                () -> assertEquals(VALID_DESCRIPTION, feedback.getDescription()),
                () -> assertEquals(VALID_RATING, feedback.getRating()));
    }

    @Test
    public void constructor_Should_ThrowException_When_TitleIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FeedbackImpl(VALID_ID, INVALID_TITLE,
                VALID_DESCRIPTION, VALID_RATING));
    }

    @Test
    public void constructor_Should_ThrowException_When_DescriptionIsShorterThanExpected() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new FeedbackImpl(VALID_ID, VALID_TITLE,
                INVALID_DESCRIPTION, VALID_RATING));
    }

    @Test
    public void status_Should_ChangeStatus_When_ValidStatus() {
        feedback.changeStatus(Status.DONE);
        Assertions.assertEquals(Status.DONE, feedback.getStatus());
    }

    @Test
    public void status_Should_ThrowException_When_InvalidStatus() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> feedback.changeStatus(Status.ACTIVE));
    }


    @Test
    public void getComments_Should_ReturnCopyOfTheCollection() {

        feedback.getComments().add(VALID_COMMENT);

        Assertions.assertEquals(0, feedback.getComments().size());
    }

    @Test
    public void getChanges_Should_ReturnCopyOfTheCollection() {
        // TODO: 18.11.2023 г. add activity when implemented
//        feedback.getChanges().add()

        Assertions.assertEquals(0, feedback.getChanges().size());
    }
}