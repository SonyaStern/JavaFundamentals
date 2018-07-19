package javase02.task3.Write;

import javase02.task3.Stationery;

public class Write extends Stationery {
    Write(int quantity) {super(quantity);}

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String GetName() {
        return name;
    }
}
