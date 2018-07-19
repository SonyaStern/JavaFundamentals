package javase02.task5;

public class Main {
    public static void main(String[] args) {
        Student s = new Student("Ivan", "Ivanov");

        Group<Student, Mark> group = new Group<>(Discipline.MATH);
        NoDiffMark mark = NoDiffMark.PASSED;
        group.addStudent(s, mark);

        Group<Student, Mark> group1 = new Group<>(Discipline.SPANISH);
        IntMark mark1 = IntMark.C;
        group1.addStudent(s, mark1);

        Group.GroupList schedule = new Group.GroupList();
        schedule.addGroup(group);
        schedule.addGroup(group1);

        for (Group<Student, Mark> g : schedule.findStudent(s)) {
            System.out.println(s.getName() + " " + s.getSurname());
            System.out.println(g.getDiscipline());
        }
    }
}
