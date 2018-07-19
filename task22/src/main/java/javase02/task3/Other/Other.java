package javase02.task3.Other;

import javase02.task3.Stationery;

public class Other extends Stationery {
    Other(int quantity) { super(quantity); }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String GetName() {
        return name;
    }


}
