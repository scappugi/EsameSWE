package DataAccess;

import DomainModel.Cart;
import DomainModel.Clothes;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

public class CartDAO {

    private HomePageDAO homepageDAO;
    private static int codorder = 0;
    private Connection connection;

    public CartDAO(String databaseURL, HomePageDAO homepageDAO) {
        this.homepageDAO = homepageDAO;
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

    public void payCartItem(Cart cart, String username) {
        int userid = -1;
        int orderid = -1;
        Map<Clothes, Integer> mymap = cart.getMap();

        //get the userid from username
        String queryuser = "SELECT codUser FROM WebUser WHERE userName = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(queryuser)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userid = resultSet.getInt("codUser");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //Create order
        codorder++;
        String queryorder = "INSERT INTO Orders(codOrder, date, shipmentDate, userID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement orderStatement = connection.prepareStatement(queryorder)) {
            orderStatement.setInt(1,codorder);
            orderStatement.setDate(2, Date.valueOf(LocalDate.now()));
            orderStatement.setDate(3,Date.valueOf(LocalDate.now().plusDays(3)));
            orderStatement.setInt(4,userid);
            orderStatement.executeUpdate();

            //get orderid
            try (ResultSet generatedkey = orderStatement.getGeneratedKeys()) {
                if (generatedkey.next())
                    orderid = generatedkey.getInt(1);
            }

            //Insert into Contains
            String querycontains = "INSERT INTO Contains(orderID, clothesID, qnt) VALUES (?, ?, ?)";
            PreparedStatement containStatement = connection.prepareStatement(querycontains);
            for(Map.Entry<Clothes, Integer> entry : mymap.entrySet()){
                Clothes clothes = entry.getKey();
                int qty = entry.getValue();

                containStatement.setInt(1,orderid);
                containStatement.setInt(2,clothes.getCodclothes());
                containStatement.setInt(3,qty);
                containStatement.addBatch();
            }
            containStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}


