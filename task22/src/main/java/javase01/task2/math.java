package javase01.task2;

import java.util.Scanner;

public class math {
    public static void main(String[] args) {
        final int n = 10000;
        double min;
        double epsilon = GetEpsilon();

        for (int i = 0; i < n; i++) {
            min = 1/(Math.pow((i+1), 2));
            if (min < epsilon) {
                System.out.println("Индекс: " + i + "  Значение: " + min);
                break;
            }
        }
    }

    public static double GetEpsilon() {
        double epsilon;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите значние (0;1)");

        while (true) {
            double temp = Double.parseDouble(scanner.next());
            if ((temp > 0) && (temp < 1)) {
                epsilon = temp;
                break;
            } else {
                System.out.println("Неверное число");
            }
        }
        return epsilon;
    }

}
