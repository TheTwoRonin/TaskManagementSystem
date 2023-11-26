package com.company.models;

import com.company.models.contracts.Comment;

public class CommentImpl implements Comment {

    private static final String TO_STRING = "Content: \"%s\" \\\\ Author: %s";
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
        return TO_STRING.formatted(getContent(), getAuthor());


    }

}
