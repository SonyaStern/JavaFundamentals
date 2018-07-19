package javase10.task3.exc;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Spliterator.ORDERED;

public interface StreamUtils {

    @NotNull
    static <T> Stream<T> toStream(ResultSet resultSet, Function<ResultSet, T> rowMapper) {
        return toStream(new Iterator<>() {
            @Override
            @SneakyThrows
            public boolean hasNext() {
                try {
                    return resultSet.isAfterLast();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            @SneakyThrows
            public T next() {
                try {
                    if (resultSet.next())
                        throw new NoSuchElementException();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //noinspection unchecked
                return rowMapper.apply(resultSet);
            }
        });
    }

    @NotNull
    static <T> Stream<T> toStream(Iterator<T> iterator) {
        return toStream(iterator, false);
    }

    @NotNull
    static <T> Stream<T> toStream(Iterator<T> iterator, boolean isParallel) {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, ORDERED),
                isParallel);
    }
/*
    @NotNull
    @Contract(" -> new")
    static <K, V> Collector<Map.Entry<K, V>, ?, Properties> toProperties() {
        //noinspection unchecked
        return new ToPropertiesCollector();
    }*/
}
