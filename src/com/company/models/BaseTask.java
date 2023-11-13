package com.company.models;

import com.company.models.contracts.Task;

public class BaseTask implements Task {
    private int id;


    @Override
    public int getId() {
        return id;
    }
}
