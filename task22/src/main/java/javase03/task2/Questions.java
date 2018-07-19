package javase03.task2;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.UnsupportedEncodingException;
import java.util.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Questions {
    static String choose_quest;
    static Locale locale;
    static int choice;
    static String quest_choice;
    static boolean flag;
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(
                "Choose language: \n" +
                "1. Rus \n" +
                "2. Eng.");

        Scanner scanner = new Scanner(System.in);
        switch (choice = scanner.nextInt()) {
            case 1: locale = new Locale("ru", "RU");
                    break;
            case 2: locale =new Locale("en", "EN");
                    break;
            default: locale = new Locale("en", "EN");
                    break;
        }

        ResourceBundle rb = ResourceBundle.getBundle("questions", locale);
        ResourceBundle rb1 = ResourceBundle.getBundle("answers", locale);

        Set<String> keys = rb.keySet();
        Set<String> keys1 = rb1.keySet();

        for (String key : keys) {
            if (!key.contains(String.valueOf(-1))) {
                System.out.println(key + ". " + rb.getString(key).getBytes("utf-8"));
            }
        }


        System.out.println(rb.getString(String.valueOf(-1)));
        quest_choice = scanner.next();

        if (quest_choice != null) {
            for (String key : keys1) {
                if ((key.contains(quest_choice)) && (!key.contains(String.valueOf(-1)))) {
                    System.out.println(key + ". " + rb1.getString(key).getBytes("utf-8"));
                    flag = true;
                }

            }
            if (!flag){
                System.out.println(rb1.getString(String.valueOf(-1)));
            }
        }

    }
}
