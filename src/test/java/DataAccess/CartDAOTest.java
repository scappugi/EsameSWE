package DataAccess;

import DomainModel.Cart;
import DomainModel.Clothes;
import DomainModel.Shirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CartDAOTest {

    @BeforeEach
    void setUp() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/ShopOnline.db");
            String delete1 = "DELETE FROM Clothes";
            String delete2 = "DELETE FROM Contains";
            String delete3 = "DELETE FROM DebitCard";
            String delete4 = "DELETE FROM Factory";
            String delete5 = "DELETE FROM Orders";
            String delete6 = "DELETE FROM WebUser";

            PreparedStatement preparedStatement1 = connection.prepareStatement(delete1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(delete2);
            PreparedStatement preparedStatement3 = connection.prepareStatement(delete3);
            PreparedStatement preparedStatement4 = connection.prepareStatement(delete4);
            PreparedStatement preparedStatement5 = connection.prepareStatement(delete5);
            PreparedStatement preparedStatement6 = connection.prepareStatement(delete6);

            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            preparedStatement3.executeUpdate();
            preparedStatement4.executeUpdate();
            preparedStatement5.executeUpdate();
            preparedStatement6.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void payCartItem() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        CartDAO cartdao = new CartDAO("C:/sqlite/ShopOnline.db", homepagedao);

        Cart cart = new Cart();
        Clothes clothes1 = new Shirt(30, "brand1", "m", "red", 1);
        Clothes clothes2 = new Shirt(30, "brand2", "m", "blue", 2);
        Clothes clothes3 = new Shirt(30, "brand2", "m", "red", 3);
        Clothes clothes4 = new Shirt(30, "brand3", "m", "red", 4);

        cart.getMap().put(clothes1, 10);
        cart.getMap().put(clothes2, 3);
        cart.getMap().put(clothes3, 5);
        cart.getMap().put(clothes4, 20);

        String query = "INSERT INTO WebUser(userName, password) VALUES  (?, ?)";
        Connection connection = homepagedao.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "user1");
            preparedStatement.setString(2, "password");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        cartdao.payCartItem(cart, "user1");
    }
}