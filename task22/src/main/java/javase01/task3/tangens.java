package javase01.task3;

public class tangens {
    public static void main(String[] args) {
        final int a = -10;
        final int b = 10;
        final int h = 2;
        double x;

        for (int i = a; i < b + 1; i+=h) {
            x = Math.tan(2*i) - 3;

            if ((x < b) && (x > a)) {
                System.out.println(i + " : " + x);
            }
        }
    }
}
