package javase02.task5;

public enum NoDiffMark implements Mark{
    PASSED("1"),
    NOT_PASSED("0");

    String type;

    NoDiffMark(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }
}
