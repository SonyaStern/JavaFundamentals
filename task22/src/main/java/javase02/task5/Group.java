package javase02.task5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Group <S extends Student, M extends Mark> {

    private Discipline discipline;
    private Map <Student, M> students = new HashMap<>();

    Group(Discipline discipline) {
        this.discipline = discipline;
    }

    void addStudent(S student, M mark){
        students.put(student, mark);
    }

    private boolean containsStudent(Student s){
        return students.containsKey(s);
    }

    Discipline getDiscipline() {
        return discipline;
    }

    static class GroupList {

        private ArrayList<Group <Student, Mark>> list;

        GroupList() {
            this.list = new ArrayList <>();
        }

        void addGroup(Group <Student, Mark> group) {
            list.add(group);
        }

        List<Group <Student, Mark>> findStudent(Student student) {
            return list.stream()
                    .filter(group -> group.containsStudent(student))
                    .collect(Collectors.toList());
        }
    }
}