package javase02.task3.Paper;

import javase02.task3.Stationery;

public class Paper extends Stationery {
    Paper(int quantity) {super(quantity);}

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String GetName() {
        return name;
    }
}
