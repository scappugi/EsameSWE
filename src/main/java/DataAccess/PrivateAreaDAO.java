package DataAccess;

import DomainModel.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrivateAreaDAO {

    private Connection connection;

    public PrivateAreaDAO() {
    }


    private Map<Clothes, Integer> getOrderDetails(int orderId) {
        Map<Clothes, Integer> orderDetails = new HashMap<>();
        PreparedStatement preparedStatement = null;

        String query = "SELECT clothesID, qnt FROM Contains WHERE orderID = ?";
        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(query);
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
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetails;
    }

    private Clothes getClothes(int clothesId) {
        Clothes clothes = null;
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM Clothes WHERE codClothes = ?";
        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clothesId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                float price = resultSet.getFloat("price");
                String brand = resultSet.getString("brand");
                String size = resultSet.getString("size");
                String color = resultSet.getString("color");
                String category = resultSet.getString("category");

                if (category.equals("shirt"))
                    clothes = new Shirt(price, brand, size, color, clothesId);
                if (category.equals("sweatshirt"))
                    clothes = new Sweatshirt(price, brand, size, color, clothesId);
                if (category.equals("trousers"))
                    clothes = new Trousers(price, brand, size, color, clothesId);
            }
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return clothes;
    }


    public void populatePrivateArea(PrivateArea privatearea, String username) {
        Map<Clothes, Integer> mymap;
        PreparedStatement preparedStatement = null;

        String query = "SELECT * FROM Orders WHERE user = ? ";

        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("codOrder");
                Date ordered = resultSet.getDate("date");
                Date shipment = resultSet.getDate("shipmentDate");
                mymap = getOrderDetails(id);

                Order order = new Order(id, ordered, shipment, username, mymap);
                privatearea.getOrders().add(order);
            }
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
