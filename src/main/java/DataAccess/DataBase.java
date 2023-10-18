package DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    public DataBase(){}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:C:/sqlite/ShopOnline.db");
    }

    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
}
