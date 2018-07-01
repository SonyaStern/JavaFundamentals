package javase01.task5;

import java.util.Scanner;

public class Matrix {
    public static void main(String[] args) {
        final int size = GetSize();
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j || i == size - 1 - j) {
                    matrix[i][j] = 1;
                }
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int GetSize() {
        int temp_size;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите размер квадратной матрицы");

        while (true) {
            int temp = Integer.parseInt(scanner.next());
            if (temp > 0) {
                temp_size = temp;
                break;
            } else {
                System.out.println("Неверное число");
            }
        }
        return temp_size;
    }
}
