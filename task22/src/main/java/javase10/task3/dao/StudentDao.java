package javase10.task3.dao;

import io.vavr.CheckedFunction1;
import io.vavr.CheckedFunction2;
import javase10.task3.cp.JDBCDao;
import javase10.task3.exc.CheckedRunnable;
import javase10.task3.exc.StreamUtils;
import javase10.task3.model.Student;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@FunctionalInterface
public interface StudentDao extends JDBCDao<Student>, Supplier<Connection> {

    String INSERT_SQL = "insert into students (name, group_id) values (?, ?)";
    String GET_ALL_SQL = "select id, name, group_id from students";
    String UPDATE_SQL = "update students set name = ?, group_id = ? where id = ?";
    String GET_SQL = "select name, group_id from students where id = ?";
    String DELETE_SQL = "delete from students where id = ?";
    String DELETE_ALL_SQL = "delete from students";
    String COUNT_SQL = "select count(id) from students";

    String ID_FIELD = "id";
    String NAME_FIELD = "name";
    String GROUP_ID_FIELD = "group_id";

    BiFunction<ResultSet, Long, Student> ROW_MAPPER = CheckedFunction2.<ResultSet, Long, Student>of(
            (resultSet, id) -> new Student(id,
                    resultSet.getString(NAME_FIELD),
                    resultSet.getInt(GROUP_ID_FIELD)))
            .unchecked();

    Function<ResultSet, Student> ID_ROW_MAPPER = CheckedFunction1.<ResultSet, Student>of(resultSet ->
            ROW_MAPPER.apply(resultSet, resultSet.getLong(ID_FIELD)))
            .unchecked();

    @NotNull
    @Override
    @SneakyThrows
    default Student save(@NotNull Student student) throws SQLException {
        @Cleanup Connection connection = get();
        @Cleanup PreparedStatement ps = connection.prepareStatement(INSERT_SQL, RETURN_GENERATED_KEYS);
        ps.setString(1, student.getName());
        ps.setInt(2, student.getGroupId());
        ps.executeUpdate();
        @Cleanup ResultSet rs = ps.getGeneratedKeys();
        if (!rs.next())
            throw new RuntimeException("Не был сгенерирован ключ!");

        //noinspection unchecked
        //return student.setId(rs.getInt(1));
        student.setId(rs.getInt(1));
        return student;
    }

    /**
     * @return Closeable Stream (!!!) of Students
     */
    @NotNull
    @Override
    @SneakyThrows
    default Stream<Student> findAll() throws SQLException {
        Connection connection = get();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_SQL);
        //noinspection unchecked
        return StreamUtils.toStream(resultSet, ID_ROW_MAPPER)
                .onClose(CheckedRunnable.of(resultSet::close).unchecked())
                .onClose(CheckedRunnable.of(statement::close).unchecked())
                .onClose(CheckedRunnable.of(connection::close).unchecked());
    }

    @NotNull
    @Override
    @SneakyThrows
    default Student update(@NotNull Student student) throws SQLException {
        @Cleanup Connection connection = get();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setInt(2, student.getGroupId());
        preparedStatement.setLong(3, student.getId());
        preparedStatement.executeUpdate();
        return student;
    }

    @NotNull
    @Override
    @SneakyThrows
    default JDBCDao<Student> delete(@NotNull Student student) throws SQLException {
        @Cleanup Connection connection = get();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
        preparedStatement.setLong(1, student.getId());
        preparedStatement.executeLargeUpdate();
        return this;
    }

    @NotNull
    @Override
    @SneakyThrows
    default Optional<Student> findById(long id) throws SQLException {
        @Cleanup Connection connection = get();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(GET_SQL);
        preparedStatement.setLong(1, id);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        //noinspection unchecked
        return Optional.ofNullable(resultSet.next() ?
                ROW_MAPPER.apply(resultSet, id) :
                null);
    }

    @NotNull
    @Override
    @SneakyThrows
    default JDBCDao<Student> clear() throws SQLException {
        @Cleanup Connection connection = get();
        @Cleanup PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_SQL);
        preparedStatement.executeLargeUpdate();
        return this;
    }

    @Override
    @SneakyThrows
    default long count() throws SQLException {
        @Cleanup Connection connection = get();
        @Cleanup Statement statement = connection.createStatement();
        @Cleanup ResultSet resultSet = statement.executeQuery(COUNT_SQL);
        return resultSet.next() ? resultSet.getLong(1) : 0L;
    }
}