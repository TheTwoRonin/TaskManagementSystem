package com.company.models.idd;

import com.company.models.contracts.Comment;

public class CommentImpl implements Comment {

    private final String content;
    private final String author;

    public CommentImpl(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Content: \"%s\" \\\\ Author: %s"
                .formatted(getContent(), getAuthor());


    }

}
