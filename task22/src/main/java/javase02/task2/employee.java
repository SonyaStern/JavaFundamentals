package javase02.task2;

import javase02.task2.stationery.*;

public class employee {
    private stationery[] stationeries;
    private int index;
    private int totalPrice;

    private employee(int items) {
        stationeries = new stationery[items];
    }

    private employee() {
        this(200);
    }

    private void addItem(stationery stationery) {
        stationeries[index++] = stationery;
        totalPrice += stationery.getPrice();
    }

    private int getTotalPrice() {
        return totalPrice;
    }

    public static void main(String[] args) {
        employee Jones = new employee();
        Jones.addItem(new notebook(100));
        Jones.addItem(new pencil(50));
        System.out.println(Jones.getTotalPrice());
    }
}
