package DataAccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class HomePageDAOTest {

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
    void registerUser() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        boolean flag = false;
        try {
            flag = homepagedao.registerUser("user1", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(flag)
            System.out.println("new user registered");
        else System.out.println("user already present");

        //test fail
        try {
            flag = homepagedao.registerUser("user1", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(flag)
            System.out.println("new user registered");
        else System.out.println("user already present");
    }

    @Test
    void login() {
    }

    @Test
    void registerCreditCard() {
    }

    @Test
    void removeCreditCard() {
    }

    @Test
    void getAllDebitCards() {
    }

    @Test
    void checkAvailability() {
    }

    @Test
    void updateAvailability() {
    }
}