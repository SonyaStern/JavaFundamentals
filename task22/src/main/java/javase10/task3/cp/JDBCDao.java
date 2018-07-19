package javase10.task3.cp;

import org.jetbrains.annotations.NotNull;
import lombok.Cleanup;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public interface JDBCDao<T extends Identifiable<T>> {

    @NotNull
    <U extends T> U save(@NotNull U t) throws SQLException;

    @NotNull
    default <U extends T> Optional<U> findById(long id) throws SQLException {
        //noinspection unchecked
        return (Optional<U>) mapAll(all -> all
                .filter(t -> t.getId() == id)
                .findAny());
    }

    @NotNull
    <U extends T> Stream<U> findAll() throws SQLException;

    default <R> R mapAll(Function<Stream<T>,R> mapper) {
        @Cleanup @NotNull Stream<T> stream = findAll();
        return mapper.apply(stream);
    }

    default void withAll(Consumer<Stream<T>> mapper) {
        @Cleanup @NotNull Stream<T> stream = findAll();
        mapper.accept(stream);
    }

    @NotNull
    <U extends T> U update(@NotNull U t) throws SQLException;

    @NotNull
    <U extends T> JDBCDao<T> delete(@NotNull U u) throws SQLException;

    @NotNull
    default JDBCDao<T> clear() throws SQLException {
        withAll(all -> all.forEach(this::delete));
        return this;
    }

    default long count() throws SQLException {
        return mapAll(Stream::count);
    }

    default boolean existsById(long id) {
        return findById(id).isPresent();
    }
}