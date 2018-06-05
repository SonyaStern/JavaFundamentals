package second;

public class task2 {
    public static void main(String[] args) {
        final int n = 10;
        double[] MyList = new double[n];
        final double epsilon = 0.1;

        for (int i = 0; i < n; i++) {
            MyList[i] = 1/(Math.pow((i+1), 2));
            if (MyList[i] < epsilon) {
                System.out.println(i + ": " + MyList[i]);
            }
        }
    }
}
