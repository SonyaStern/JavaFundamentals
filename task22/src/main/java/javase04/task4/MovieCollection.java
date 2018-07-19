package javase04.task4;

import java.io.*;
import java.util.ArrayList;

public class MovieCollection implements Serializable {
    private static ArrayList<Movie> movies = new ArrayList<>();

    private static void addMovie(Movie movie) {
        movies.add(movie);
    }

    static void removeMovie(Movie movie) {
        movies.remove(movie);
    }

    private static void getMovies(File file) {
        try( ObjectInputStream objin = new ObjectInputStream(new FileInputStream(file))) {
            while (objin.available() > 0) {
                movies.add((Movie)objin.readObject());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void createMovieFile(File file) {
        try(ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Movie movie: movies) {
                objout.writeObject(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Movie movie = new Movie("title", 1956);
        Actor actor = new Actor("First", "name");
        movie.addActor(actor);
        addMovie(movie);
        File file = new File("G:\\Studies\\5 sem\\апплеты\\lab1\\lab1\\src\\movies.txt");
        MovieCollection.getMovies(file);
        MovieCollection.createMovieFile(file);
    }

}