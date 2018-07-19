package javase02.task3;

import javase02.task3.Paper.Notebook;
import javase02.task3.Write.Pen;
import javase02.task3.Write.Pencil;


public class Main {

    private Stationery[] stationeries;
    private int index;
    private int totalQuantity;

    private Main(int items) {
        stationeries = new Stationery[items];
    }

    private Main() {
        this(200);
    }

    private void addItem(Stationery stationery) {
        stationeries[index++] = stationery;
        totalQuantity += stationery.getQuantity();
    }

    private void printAll() {
        for (int i = 0; i < index; i++) {
            System.out.println(stationeries[i].GetName() + " | " + stationeries[i].getQuantity());
        }
    }

    private int GetTotalQuantity() {
        return totalQuantity;
    }

    public static void main(String[] args) {
        Main BegginerSet = new Main();
        BegginerSet.addItem(new Pen(30));
        BegginerSet.addItem(new Pencil(20));
        BegginerSet.addItem(new Notebook(10));
        BegginerSet.printAll();
    }


}