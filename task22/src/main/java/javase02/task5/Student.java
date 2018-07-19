package javase02.task5;

public class Student {
    public String name;
    private String surname;

    public String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
