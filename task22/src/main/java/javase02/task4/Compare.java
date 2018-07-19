package javase02.task4;

import javase02.task3.Stationery;
import javase02.task3.Write.Pen;
import javase02.task3.Write.Pencil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Compare {

    /*private static Comparator<Stationery> ByQuantity =  new Comparator <Stationery>() {
        @Override
        public int compare(Stationery s1, Stationery s2) {
            return s1.getQuantity() - s2.getQuantity();
        }
    };*/

    private static Comparator<Stationery> ByQuantity = Comparator.comparingInt(Stationery::getQuantity);

    /*private static Comparator<Stationery> ByName =  new Comparator <Stationery>() {
        @Override
        public int compare(Stationery s1, Stationery s2) {
            return s1.GetName().compareTo(s2.GetName());
        }
    };*/

    private static Comparator<Stationery> ByName = Comparator.comparing(Stationery::GetName);

    private static Comparator<Stationery> ByQuantityAndName = Comparator
            .comparingInt(Stationery::getQuantity).thenComparing(ByName);




    private static void printAll(ArrayList<Stationery> list) {
        for(Stationery s : list){
            System.out.println(s.GetName() + " | " + s.getQuantity());
        }
    }

    public static void main(String[] args) {
        ArrayList<Stationery> Set = new ArrayList <>();
        Set.add(new Pen(30));
        Set.add(new Pen(5));
        Set.add(new Pen(44));
        Set.add(new Pencil(5));

        Set.sort(ByQuantityAndName);
        printAll(Set);
    }
}