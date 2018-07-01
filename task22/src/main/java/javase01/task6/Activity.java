package javase01.task6;

/**
 * Класс описывает работу с блокнотом
 *
 * Выводит меню циклично
 *
 * version 1.2
 *
 * @author Sofya Krylova
 *
 */

import java.util.Scanner;
import java.util.TreeMap;

public class Activity {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Notebook notebook = new Notebook();
        System.out.println("Добро пожаловать в блокнот!\n");
        int sel;
        String note;
        do {
            Activity.menu();
            sel = Activity.scanner.nextInt();

            if ((sel > -1) && (sel < 5)) {
                switch (sel) {
                    case 1: {
                        System.out.println("Введите заметку.");
                        note = Activity.scanner.next();
                        notebook.addNote(note);
                        System.out.println("Ваша заметка было добавлена под номером " + Note.counter + ".");
                        break;
                    }
                    case 2: {
                        System.out.println("Введите номер удаляемой заметки.");
                        int key = Activity.scanner.nextInt();
                        notebook.deleteNote(key);
                        System.out.println("Ваша заметка было удалена.");
                        break;
                    }
                    case 3: {
                        System.out.println("Введите номер обновляемой заметки.");
                        int key = Activity.scanner.nextInt();
                        System.out.println("Введите новый текст заметки.");
                        note = Activity.scanner.next();
                        notebook.updateNote(key, note);
                        System.out.println("Ваша заметка была обновлена.");
                        break;
                    }
                    case 4: {
                        TreeMap<Integer, String> notes = notebook.printAllNotes();
                        System.out.println(notes.keySet() + " " + notes.values());

                        break;
                    }
                    case 0: {
                        System.out.println("До свидания!");
                        break;
                    }
                    default:
                        System.out.println("Неверный номер.");
                }
            } else {
                System.out.println("Неверное число.");
            }
        }
        while (sel != 0);
        Activity.scanner.close();
    }

    private static void menu() {
        System.out.println("Выберите действие.\n");
        System.out.println("1. Добавить заметку.");
        System.out.println("2. Удалить заметку.");
        System.out.println("3. Обновить заметку.");
        System.out.println("4. Показать все заметки.");
        System.out.println("0. Выход из меню.\n");

    }
}
