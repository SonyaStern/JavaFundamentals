package javase06;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Unimap {
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

    private static Map<String, String> getProperty(String path) {
        properties = getFile(path);
        Map<String, String> propertyMap = new HashMap<>();
        if (properties != null) {
            Enumeration<?> e = properties.propertyNames();
            while (e.hasMoreElements()) {
                String key = (String)e.nextElement();
                propertyMap.put(key, properties.getProperty(key));
            }
        }
        return propertyMap;
    }

    public static String getPropertyByName(String path) {
        System.out.println("Write a property.");
        Scanner scanner = new Scanner(System.in);
        String propertyName = scanner.nextLine();
        String foundProperty = null;
        try {
            Map<String, String> propertyMap = getProperty(path);
            if (propertyMap.containsKey(propertyName))
                foundProperty = propertyMap.get(propertyName);
            else System.out.println("No such property");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return foundProperty;
    }

}
