package javase02.task2;

public abstract class stationery {
    int price;

    stationery(int price) {
        this.price = price;
    }

    public abstract int getPrice();

    public static class notebook extends stationery {
        notebook(int price) {
            super(price);
        }

        @Override
        public int getPrice() {
            return price;
        }
    }

    public class pen extends stationery {
        public pen(int price) {
            super(price);
        }

        @Override
        public int getPrice() {
            return price;
        }
    }

    public static class pencil extends stationery {
        pencil(int price) {
            super(price);
        }

        @Override
        public int getPrice() {
            return price;
        }
    }

    public static class scisors extends stationery {
        public scisors(int price) {
            super(price);
        }

        @Override
        public int getPrice() {
            return price;
        }
    }

    public static class adhesive_tape extends stationery {
        public adhesive_tape(int price) {
            super(price);
        }

        @Override
        public int getPrice() {
            return price;
        }
    }
}

