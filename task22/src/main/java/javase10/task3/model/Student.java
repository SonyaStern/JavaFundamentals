package javase10.task3.model;

import javase10.task3.cp.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public abstract class Student implements Identifiable<Student> {

    long id;
    String name;
    int groupId;

    public Student(String name, int groupId) {
        this(0L, name, groupId);
    }

}