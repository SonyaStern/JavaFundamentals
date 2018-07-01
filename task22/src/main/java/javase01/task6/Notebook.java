package javase01.task6;

/**
 * Класс описывает блокнот
 *
 * version 1.2
 *
 * @author Sofya Krylova
 *
 */


import java.util.TreeMap;


public class Notebook {
    /**
     * Содержит записи блокнота
     */
    TreeMap<Integer, String> notes = new TreeMap<>();

    /**
     * Добавление заметки
     *
     * @param text текст заметки
     *
     * @see Note
     */
    public void addNote(String text) {
        Note note = new Note(text);
        notes.put(note.key, note.text);
    }

    /**
     * Удаление заметки
     *
     * @param key номер удаляемой заметки
     */
    public void deleteNote(int key) {
        notes.remove(key);
    }

    /**
     * Обновление заметки
     *
     * @param key номер обновляемой заметки
     * @param text новый текст заметки
     */
    public void updateNote(int key, String text) {
        notes.put(key, text);
    }

    /**
     * Вывод всех заметов
     *
     * @return все заметки
     */
    public TreeMap printAllNotes() {
       return notes;
       /*for (int i = 0; i < counter; i++) {
           System.out.println("| " + notes.getKey);
       }*/
        //notes.forEach((key, value) -> return("| " + key + " | " + value + " |"));


    }
}