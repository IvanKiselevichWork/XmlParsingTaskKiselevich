package by.kiselevich.xmlparser.util;

import java.util.Random;

public class CookieGenerator {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_LENGTH = 64;

    public static char[] generateCookie(int length) {
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            Random random = new Random();
            result[i] =  ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        return result;
    }

    public static char[] generateCookie() {
        return generateCookie(DEFAULT_LENGTH);
    }
}
