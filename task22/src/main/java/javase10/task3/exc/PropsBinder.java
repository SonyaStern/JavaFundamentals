package javase10.task3.exc;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

// Returns class object initialized with properties from <fileName>.properties
public interface PropsBinder {
    static <T> T from(Class<T> tClass) throws IllegalAccessException, InstantiationException, InvocationTargetException, IOException {
        return from(tClass.getSimpleName(), tClass);
    }

    @SneakyThrows
    static <T> T from(String fileName, Class<T> tClass) throws IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        // Gets all properties from resources/<fileName>.properties
        Properties properties = new Properties();
        @Cleanup InputStream inputStream = PropsBinder.class.getResourceAsStream(String.format("/%s.properties", fileName));
        properties.load(inputStream);
        // Gets a constructor with the largest amount of arguments,
        // because we don't know the exact amount of arguments
        Constructor<T> constructor = (Constructor<T>)Arrays.stream(tClass.getConstructors())
                .max(Comparator.comparingInt(Constructor::getParameterCount))
                .orElseThrow(() -> new RuntimeException("There is no constructor!"));
        // Matching constructor with properties (we create new object with this properties)
        // We should place each property to its place (matching for name)
        Object[] paramValues = Arrays.stream(constructor.getParameters())
                .map(parameter -> resolveParameter(parameter,
                        properties.get(parameter.getName()).toString()))
                .toArray();
        // Finally, returns the constructor
        return constructor.newInstance(paramValues);
    }

    // Returns the type of parameter with value
    private static Object resolveParameter(Parameter parameter, String value) {
        Class<?> parameterType = parameter.getType();
        if (parameterType == String.class)
            return value;
        if (parameterType == int.class || parameterType == Integer.class)
            return Integer.parseInt(value);
        if (parameterType == double.class || parameterType == Double.class)
            return Double.parseDouble(value);
        if (parameterType == long.class || parameterType == Long.class)
            return Long.parseLong(value);
        return value;
    }
}
