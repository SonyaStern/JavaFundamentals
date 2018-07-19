package javase02.task5;

public enum  IntMark implements Mark {

    A("5"),
    B("4"),
    C("3"),
    D("2");

    final String type;

    IntMark(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
