package com.board.utils;

import com.board.utils.models.Credentials;

public class UserGenerator {
    public static Credentials generateNewUser() {
        String email = DataGenerator.generateRandomEmail();
        String password = "Password123!";

        return new Credentials(email, password);
    }
}
