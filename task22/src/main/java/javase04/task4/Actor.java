package javase04.task4;

import java.io.Serializable;

public class Actor implements Serializable {
    private static String name;
    private String surname;

    Actor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public static String getName() {
        return name;
    }
}
