package javase10.task3.cp;

import io.vavr.CheckedFunction1;
import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.Function2;
import javase10.task3.exc.CheckedFunction3;
import javase10.task3.exc.PropsBinder;
import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

/* Class ConnectionPool returns connection pool. So we use Supplier interface.
It takes no arguments and returns object of Connection class */
@FieldDefaults(level = PRIVATE)
public class ConnectionPool implements Supplier<Connection>, Closeable {

    final BlockingQueue<PooledConnection> freeConnections;
    volatile boolean isOpened = true;

    @SuppressWarnings("unused")
    public ConnectionPool(String url, String user, String password, int poolSize, String sqlFolder) throws SQLException {
        this(
                CheckedFunction3
                        .<String, String, String, Connection>of(DriverManager::getConnection)
                        .supply(url, user, password),
                poolSize,
                sqlFolder);
    }

    private ConnectionPool(Function0<Connection> connectionCreator, int poolSize, String sqlFolder) throws SQLException {

        Supplier<PooledConnection> pooledConnectionCreator = connectionCreator
                .andThen(Function2.of(PooledConnection::new)
                        .apply(this::closer));

        freeConnections = IntStream.iterate(0, operand -> operand + 1)
                .limit(poolSize)
                .mapToObj(__ -> pooledConnectionCreator.get())
                .collect(Collectors.toCollection(() -> new ArrayBlockingQueue<>(poolSize)));

        Function1<String, Optional<String>> getFileAsString =
                Function2.narrow(ConnectionPool::getSqlFileAsString)
                        .apply(sqlFolder);

        execute(IntStream.iterate(1, operand -> operand + 1)
                .mapToObj(String::valueOf)
                .map(getFileAsString)
                .takeWhile(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.joining()));
    }

    @NotNull
    public static ConnectionPool fromProps() throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return fromProps("jdbc");
    }

    @NotNull
    public static ConnectionPool fromProps(String jdbc) throws InvocationTargetException, IOException, InstantiationException, IllegalAccessException {
        return PropsBinder.from(jdbc, ConnectionPool.class);
    }

    @SneakyThrows
    private static Optional<String> getSqlFileAsString(String initScriptsPath, String name) {
        String path = String.format("/%s/%s.sql", initScriptsPath, name);

        Function<Path, String> read =
                CheckedFunction1.<Path, String>of(filePath -> {
                    @Cleanup Stream<String> lines = Files.lines(filePath);
                    return lines.collect(Collectors.joining());
                }).unchecked();

        return Optional.ofNullable(ConnectionPool.class.getResource(path))
                .map(CheckedFunction1.narrow(URL::toURI).unchecked())
                .map(Paths::get)
                .map(read);
    }

    @SneakyThrows
    public void execute(String sql) throws SQLException {
        @Cleanup Connection connection = get();
        @Cleanup Statement statement = connection.createStatement();
        statement.executeUpdate(sql);
    }

    @SneakyThrows
    private void closer(PooledConnection pooledConnection) throws SQLException, InterruptedException {
        if (isOpened) {
            if (!pooledConnection.getAutoCommit())
                pooledConnection.setAutoCommit(true);
            if (pooledConnection.isReadOnly())
                pooledConnection.setReadOnly(false);
            freeConnections.put(pooledConnection);
        } else
            pooledConnection.reallyClose();
    }

    @Override
    @SneakyThrows
    public Connection get() {
        if (isOpened) {
            try {
                return freeConnections.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else
            try {
                throw new IOException("Connection Pool is already closed!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        return null;
    }

    @Override
    public void close() {
        isOpened = false;
        freeConnections.forEach(PooledConnection::close);
    }
}