package javase02.task6;

@SubmarinAnno
public class Submarin {
    public String name;
    public int passengers;
    private Motor motor;

    public String getName() {
        return name;
    }

    private Submarin() {
        motor = new Motor();
    }

    public static class Motor {
        public String type;
        public int power;
        boolean isStarted;

        void start() {
            if (!isStarted) {
                isStarted = true;
                System.out.println("Motor is started! Well done.");
            } else {
                System.out.println("Motor was already started.");
            }
        }

        void stop() {
            if (!isStarted) {
                System.out.println("Motor is already stopped");
            } else {
                isStarted = false;
                System.out.println("Motor finished its journey.");
            }
        }
    }

    private void SubmarinStart() {
        motor.start();
        System.out.println("Good journey!");
    }

    public void SubmarinStop() {
        motor.stop();
        System.out.println("Welcome!");
    }

    public static void main(String[] args) {
        Submarin Mary = new Submarin();
        Mary.SubmarinStart();
    }
}
