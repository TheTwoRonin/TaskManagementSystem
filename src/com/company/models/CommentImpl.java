package com.company.models;

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
}
