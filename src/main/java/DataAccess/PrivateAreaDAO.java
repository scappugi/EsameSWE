package DataAccess;

import DomainModel.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrivateAreaDAO {

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

    private Map<Clothes, Integer> getOrderDetails(int orderId) {
        Map<Clothes, Integer> orderDetails = new HashMap<>();

        String query = "SELECT clothesID, qnt FROM Contains WHERE orderID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, orderId);
            ResultSet containsResultSet = preparedStatement.executeQuery();
            while (containsResultSet.next()) {
                int clothesId = containsResultSet.getInt("clothesID");
                int quantity = containsResultSet.getInt("qnt");
                Clothes clothes = getClothes(clothesId);

                if (clothes != null) {
                    orderDetails.put(clothes, quantity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetails;
    }

    private Clothes getClothes(int clothesId) {
        Clothes clothes = null;
        String query = "SELECT * FROM Clothes WHERE codClothes = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, clothesId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                float price = resultSet.getFloat("price");
                String brand = resultSet.getString("brand");
                String size = resultSet.getString("size");
                String color = resultSet.getString("color");
                String category = resultSet.getString("category");

                if(category.equals("shirt"))
                    clothes = new Shirt(price, brand, size, color);
                if(category.equals("sweatshirt"))
                    clothes = new Sweatshirt(price, brand, size, color);
                if(category.equals("trousers"))
                    clothes = new Trousers(price, brand, size, color);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clothes;
    }


    public void populatePrivateArea(PrivateArea privatearea, String username) {
        Map<Clothes, Integer> mymap;

        String query = "SELECT * FROM Orders,WebUser WHERE userName = ? AND codUser = Orders.userID";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            System.out.println(getUserIdByUsername(username));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("codOrder");
                Date ordered = resultSet.getDate("date");
                Date shipment = resultSet.getDate("shipmentDate");
                mymap = getOrderDetails(id);

                Order order = new Order(id, ordered, shipment, username, mymap);
                privatearea.getOrders().add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getUserIdByUsername(String username) throws SQLException {
        String selectQuery = "SELECT codUser FROM WebUser WHERE userName = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("codUser");
        }

        return -1;
    }
}
