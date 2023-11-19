package com.company.models;

import com.company.models.contracts.Activity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActivityImpl implements Activity {
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
    private final String description;
    private final LocalDateTime timestamp;

    public ActivityImpl(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }

        this.description = description;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String getAsString() {
        return String.format("[%s] %s", timestamp.format(FORMATTER), description);
    }
}
