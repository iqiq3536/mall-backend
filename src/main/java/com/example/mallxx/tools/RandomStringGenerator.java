package com.example.mallxx.tools;

import java.util.Random;

public class RandomStringGenerator {

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static final Random random = new Random();

    public static String generateRandomString(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("String length must be positive");
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            // Generate a random index to pick characters
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

            // Append the character to the StringBuilder
            sb.append(rndChar);
        }
        return sb.toString();
    }
}
