package com.board.utils;

import java.util.Random;

public class DataGenerator {
    private static final Random random = new Random();
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomEmail() {
        return "testuser_" + System.currentTimeMillis() + "_" +
                generateRandomString(5) + "@test.com";
    }

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));
        }
        return sb.toString();
    }

    public static String generateRandomTitle() {
        return "Advertisement " + System.currentTimeMillis();
    }

    public static String generateRandomDescription() {
        return "Advertisement description " + System.currentTimeMillis();
    }

    public static int generateRandomPrice() {
        return random.nextInt(10000) + 100;
    }
}