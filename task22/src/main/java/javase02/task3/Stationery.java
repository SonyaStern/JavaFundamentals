package javase02.task3;

public abstract class Stationery {
    public int quantity;
    public String name;

    public Stationery(int quantity) {
        this.quantity = quantity;
    }

    public abstract int getQuantity();

    public abstract String GetName();
}

