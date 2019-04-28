package com.company;

import java.util.Random;

/**
 *
 */
public class Generator {

    private static final String CHAR_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-";

    /**
     * генерирует случайный текст.
     * @param length - длина генерируемого текста.
     * @return - сгенерированный текст.
     */
    public static String randomText(int length) {

        StringBuilder builder = new StringBuilder();
        while (length -- != 0) {
            int character = (int)(Math.random() * CHAR_STRING.length());
            builder.append(CHAR_STRING.charAt(character));
        }
        String result = builder.toString();

        System.out.println("Generated text: " + result);

        return result;
    }

    /**
     * генерирует случайную почту.
     * @param mail_service - почтовая служба (e.g. 'gmail', 'mail', 'rambler' etc).
     * @param domain - домен (e.g. 'com', 'ru', 'ua' etc).
     * @return - сгенерированную почту.
     */
    public static String randomEmail(String mail_service, String domain) {

        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        while (builder.length() < 10) {
            int character = (int) (rnd.nextFloat() * CHAR_STRING.length());
            builder.append(CHAR_STRING.charAt(character));
        }
        String result = builder.toString() + "@" + mail_service + "." + domain;

        System.out.println("Generated email: " + result);

        return result;
    }
}
