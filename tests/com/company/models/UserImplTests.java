package com.company.models;

import com.company.models.contracts.User;
import com.company.utils.TestUtilities;

public class UserImplTests {
    public static User initializeTestUser() {
        return new UserImpl(1, TestUtilities.getString(5));
    }
}
