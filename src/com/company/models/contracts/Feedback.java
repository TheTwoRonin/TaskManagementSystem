package com.company.models.contracts;

public interface Feedback extends Task {

    int getRating();

    void changeRating(int rating);
}
