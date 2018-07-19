package javase10.task3.cp;

public interface Identifiable<T> {
    long getId();
    T setId(long id);
}