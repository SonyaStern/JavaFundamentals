package javase10.task3.cp;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Consumer;

import static lombok.AccessLevel.PRIVATE;

@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class PooledConnection implements Connection {

    Consumer<PooledConnection> closer;

    @Delegate(excludes = AutoCloseable.class)
    Connection connection;

    @Override
    public void close() {
        closer.accept(this);
    }

    @SneakyThrows
    public void reallyClose() throws SQLException {
        connection.close();
    }
}