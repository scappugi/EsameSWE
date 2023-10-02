package DataAccess;

import DomainModel.Order;
import DomainModel.PrivateArea;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class PrivateAreaDAO {

    public PrivateAreaDAO() {
    }

    private Connection connection;

    public PrivateAreaDAO(String databaseURL) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //manca il modifyDaty() non so a cosa serve
    public ArrayList<Order> getAllOrder() {
        ArrayList<Order> orders = null;
        return orders;
    }

    public Order getOrder(Order o) {
        return o;
    }

    public boolean addOrder() {
        return true;
    }

    public void populatePrivateArea(PrivateArea privatearea, String username) {
        String query = "SELECT * FROM Orders,WebUser WHERE userName = ? AND codUser = Orders.userID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String id = resultSet.getString("codOrder");
                Date ordered = resultSet.getDate("date");
                Date shipment = resultSet.getDate("shipmentDate");

                Order order = new Order(id, ordered, shipment, username);
                privatearea.getOrders().add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
