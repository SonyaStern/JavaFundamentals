package javase01.task6;

/**
 * Класс описывает запись в блокноте
 *
 * version 1.2
 *
 * @author Sofya Krylova
 *
 */

public class Note {
    String text;
    int key;
    static int counter = 0;

    public Note(String text) {
        this.text = text;
        counter++;
        key = counter;
    }
}