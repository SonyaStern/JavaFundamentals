package javase07.task2;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.locks.ReentrantLock;

public class second_synchron {
    private static final Object object = new ReentrantLock();
    private static ResourceBundle getProperty(String path){
        ResourceBundle property;
        synchronized (object) {
            property = ResourceBundle.getBundle(path);
            return property;
        }
    }

    private static Map<String, String> getFromFile() {
        ResourceBundle property = getProperty("properties");
        if (property != null) {
            Enumeration<?> enumeration = property.getKeys();
            Map<String, String> propertyMap = new HashMap<>();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                propertyMap.put(key, property.getString(key));
            }
            return propertyMap;
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(second_synchron.getFromFile());
    }
}
