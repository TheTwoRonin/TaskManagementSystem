package com.company.models;

import com.company.models.contracts.Log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Activity implements Log {
    private static final String DESCRIPTION_CANNOT_BE_EMPTY_ERR = "Description cannot be empty";
    private static final String TO_STRING = "[%s] %s";

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
    private final String description;
    private final LocalDateTime timestamp;

    public Activity(String description) {
        checkDescription(description);
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    private void checkDescription(String description){
        if (description.isEmpty()) {
            throw new IllegalArgumentException(DESCRIPTION_CANNOT_BE_EMPTY_ERR);
        }
    }

    @Override
    public String toString() {
        return String.format(TO_STRING, timestamp.format(FORMATTER), description);
    }
}
