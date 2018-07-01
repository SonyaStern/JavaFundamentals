package javase02.task1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Pen {
    String type;
    String color;
    int price;

   public Pen (String type, String color, int price) {
        this.type = type;
        this.color = color;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Pen pen = (Pen) obj;
        if (type != pen.type)
            return false;
        if (color != pen.color)
            return false;
        if (price != pen.price)
            return false;
        return true;
    }

     @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + type.hashCode();
        result = prime * result + color.hashCode();
        result = prime * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Pen | " + type + " | " + color + " | " + price;
    }

    public static void main(String[] args) {
        Pen pen = new Pen("Pilot", "blue", 100);
        System.out.println(pen.toString());
    }
}
