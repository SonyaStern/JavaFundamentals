package javase01.task4;

public class MaxElement {
    public static void main(String[] args) {
        int n = 30;
        int[] matrix = new int[n];
        int[] matrixb = new int[n/2];
        int max = 0;

        for (int i = 0; i < n; i++) {
            matrix[i] = i + 1;
        }
        int i = 0;
        if (n % 2 == 0) {
            for (int j = 1; j < (n/2) + 1; j++) {
                matrixb[i] = matrix[i] + matrix[n - j];

                System.out.println(matrix[i] + " " + matrix[n - j] + " " + matrixb[i]);

                if (matrixb[i] > max) {
                    max = matrixb[i];
                }
                i++;
            }
        } else {
            for (int j = 1; j < (n/2) + 1; j++) {
                matrixb[i] = matrix[i] + matrix[n - j];

                System.out.println(matrix[i] + " " + matrix[n - j] + " " + matrixb[i]);

                if (matrixb[i] > max) {
                    max = matrixb[i];
                }
                i++;
            }
        }

        System.out.println(max);
    }
}
