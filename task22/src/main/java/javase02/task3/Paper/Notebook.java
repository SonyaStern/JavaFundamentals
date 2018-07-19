package javase02.task3.Paper;

public class Notebook extends Paper {
    public Notebook(int quantity) { super(quantity); }

    @Override
    public String GetName() {
        return "Notebook";
    }
}
