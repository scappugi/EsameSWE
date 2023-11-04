package DataAccess;

import DomainModel.Cart;
import DomainModel.Clothes;

import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

public class CartDAO {

    private HomePageDAO homepageDAO;
    private static int codorder = 1;
    private Connection connection;

    public CartDAO(HomePageDAO homepageDAO) {
        this.homepageDAO = homepageDAO;
    }


    public boolean payCartItem(Cart cart, String username) {
        Map<Clothes, Integer> mymap = cart.getMap();
        boolean flag = false;
        PreparedStatement orderStatement = null;
        Connection connection = null;
        int qty;

        //Create order
        String queryorder = "INSERT INTO Orders(codOrder, date, shipmentDate, user) VALUES (?, ?, ?, ?)";
        try {
            codorder++;
            connection = DataBase.getConnection();
            orderStatement = connection.prepareStatement(queryorder);
            orderStatement.setInt(1, codorder);
            orderStatement.setDate(2, Date.valueOf(LocalDate.now()));
            orderStatement.setDate(3, Date.valueOf(LocalDate.now().plusDays(3)));
            orderStatement.setString(4, username);
            orderStatement.executeUpdate();
            DataBase.closeConnection(connection);
            //Insert into Contains
            String querycontains = "INSERT INTO Contains(orderID, clothesID, qnt) VALUES (?, ?, ?)";
            connection = DataBase.getConnection();
            PreparedStatement containStatement = connection.prepareStatement(querycontains);
            for (Map.Entry<Clothes, Integer> entry : mymap.entrySet()) {
                Clothes clothes = entry.getKey();
                qty = entry.getValue();
                containStatement.setInt(1, codorder);
                containStatement.setInt(2, clothes.getCodclothes());
                containStatement.setInt(3, qty);
                containStatement.executeUpdate();
            }
            DataBase.closeConnection(connection);
            flag = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }

}
