package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
//manca costruttore
    private static String jdbcUrl = "jdbc:postgresql://localhost:5432/ShopOnline";
    private static String username = "postgres";
    private static String password = "fiorentina";

    public static Connection getConnection()  throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }


}
