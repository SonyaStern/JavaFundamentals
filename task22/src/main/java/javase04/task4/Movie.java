package javase04.task4;

import java.util.ArrayList;

public class Movie {
    private static String title;

    private int year;
    private static ArrayList<Actor> actors;

    Movie(String title, int year) {
        this.title = title;
        this.year = year;
        actors = new ArrayList<>();
    }

    static String getName() {
        return title;
    }

    static String getActor() {
        return Actor.getName();
    }

    static void addActor(Actor actor) {
        actors.add(actor);
    }

    public void removeActor(Actor actor) {
        actors.remove(actor);
    }
}
