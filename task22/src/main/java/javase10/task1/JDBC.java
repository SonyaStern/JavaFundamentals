package javase10.task1;

import java.sql.*;

public class JDBC {
    private static String URL = "jdbc:h2:mem:db; DB_CLOSE_DELAY=-1";
    private static String CREATE_TABLE = "CREATE TABLE music (" +
            "id IDENTITY," +
            "singer VARCHAR," +
            "title VARCHAR," +
            "year INT";
    private static String INSERT1 = "INSERT INTO music (singer, title, year) VALUES (ATL, Mike, 2017)";
    private static String INSERT2 = "INSERT INTO music (singer, title, year) VALUES (Loqiemean, Queen, 2018)";
    private static String SELECT_ALL = "SELECT singer, title, year FROM music";
    private static String PREP_SELECT = "SELECT singer, title, year FROM music WHERE title = ?";
    private static String PREP_UPDATE = "UPDATE music SET singer = ? WHERE title = ?";
    private static String DROP = " DROP TABLE music";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL);
        Statement statement = connection.createStatement();

        statement.executeUpdate(CREATE_TABLE);
        statement.executeUpdate(INSERT1);
        statement.executeUpdate(INSERT2);

        PreparedStatement preparedStatement = connection.prepareStatement(PREP_SELECT);
        preparedStatement.setString(1, "Mike");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.toString());
        }

        PreparedStatement preparedStatement1 = connection.prepareStatement(PREP_UPDATE);
        preparedStatement1.setString(1, "Noize MC");
        preparedStatement1.setString(2, "Mile");
        preparedStatement1.executeUpdate();

        ResultSet resultSet1 = statement.executeQuery(SELECT_ALL);
        while (resultSet1.next()) {
            System.out.println(String.format("%d : %s : %s : %d", resultSet1.getInt(1), resultSet1.getString(2),
                    resultSet1.getString(3), resultSet1.getInt(4)));
        }

        statement.execute(DROP);
        statement.close();
        preparedStatement.close();
        preparedStatement1.close();
        connection.close();
    }

}
