package com.company;

import com.company.core.TaskManagementSystemEngineImpl;

public class Startup {

    public static void main(String[] args) {
        TaskManagementSystemEngineImpl engine = new TaskManagementSystemEngineImpl();
        engine.start();
    }
}
