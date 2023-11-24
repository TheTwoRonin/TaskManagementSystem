package com.company.models;

import com.company.models.contracts.Comment;
import com.company.utils.CommandConstraints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommentImplTests {
    Comment comment;

    @BeforeEach
    public void setUp() {
        comment = new CommentImpl(CommandConstraints.VALID_CONTENT, CommandConstraints.VALID_AUTHOR);
    }

    @Test
    public void should_CreateComment_When_ArgumentsAreValid() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(CommandConstraints.VALID_CONTENT, comment.getContent()),
                () -> Assertions.assertEquals(CommandConstraints.VALID_AUTHOR, comment.getAuthor()));
    }
}
