package javase05.task2;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;

public class Uniclass {
    private static Properties properties = new Properties();
    private static Properties getFile(String path) {
        try(InputStreamReader reader = new FileReader(path)) {
            properties.load(reader);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static void getProperty() {
        System.out.println("Write a property.");
        Scanner scanner = new Scanner(System.in);
        String propertyName = scanner.nextLine();
        try {
            String property = properties.getProperty(propertyName);
            System.out.println(property);
            if (property == null) System.out.println("Property wasn't found");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        String path = "C:\\Users\\Stern\\IdeaProjects\\task22\\src\\main\\java\\javase03\\task2\\Property\\answers.properties";
        getFile(path);
        getProperty();
    }
}
