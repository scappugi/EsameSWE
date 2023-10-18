package DataAccess;

import DomainModel.Clothes;
import DomainModel.DebitCard;
import DomainModel.RegisteredWebUser;
import DomainModel.Shirt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HomePageDAOTest {

    @BeforeEach
    void setUp() {
        try {
            Connection connection = DataBase.getConnection();
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
            DataBase.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void registerUser() {
        HomePageDAO homepagedao = new HomePageDAO();

        try {
            assertEquals(true, homepagedao.registerUser("user1", "password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void login() {
        HomePageDAO homepagedao = new HomePageDAO();

        try {
            assertEquals(true, homepagedao.registerUser("user1", "password"));
            assertNotNull(homepagedao.login("user1", "password"));
            assertNull(homepagedao.login("user2", "password2"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void registerCreditCard() {
        HomePageDAO homepagedao = new HomePageDAO();

        try {
            assertEquals(true, homepagedao.registerUser("user1", "password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DebitCard card = new DebitCard(1, 111, Date.valueOf(LocalDate.now()));
        assertEquals(true, homepagedao.registerCreditCard(card, "user1"));

    }

    @Test
    void removeCreditCard() {
        HomePageDAO homepagedao = new HomePageDAO();

        try {
            assertEquals(true, homepagedao.registerUser("user1", "password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DebitCard card = new DebitCard(1, 111, Date.valueOf(LocalDate.now()));
        assertEquals(true, homepagedao.registerCreditCard(card, "user1"));
        assertEquals(true, homepagedao.removeCreditCard(1, 111, "user1"));


    }

    @Test
    void getAllDebitCards() {
        HomePageDAO homepagedao = new HomePageDAO();

        try {
            assertEquals(true, homepagedao.registerUser("user1", "password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DebitCard card = new DebitCard(1, 111, Date.valueOf(LocalDate.now()));
        assertEquals(true, homepagedao.registerCreditCard(card, "user1"));
        DebitCard card2 = new DebitCard(2, 222, Date.valueOf(LocalDate.now()));
        assertEquals(true, homepagedao.registerCreditCard(card2, "user1"));

        ArrayList<DebitCard> result = homepagedao.getAllDebitCards("user1");
        assertEquals(2, result.size());
    }

    @Test
    void checkAvailability() {
        HomePageDAO homepagedao = new HomePageDAO();
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String query1 = "INSERT INTO Clothes(codClothes, COLOR, CATEGORY, BRAND, SIZE, factory, QTY, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "red");
            preparedStatement.setString(3, "shirt");
            preparedStatement.setString(4, "brand1");
            preparedStatement.setString(5, "m");
            preparedStatement.setString(6, "f1");
            preparedStatement.setInt(7, 10);
            preparedStatement.setFloat(8, 20);
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clothes clothes1 = new Shirt(20, "brand1", "m", "red", 1);
        assertEquals(10, homepagedao.checkAvailability(clothes1));

    }

    @Test
    void updateAvailability() {
        HomePageDAO homepagedao = new HomePageDAO();
        Connection connection = null;
        try {
            connection = DataBase.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String query1 = "INSERT INTO Clothes(codClothes, COLOR, CATEGORY, BRAND, SIZE, factory, QTY, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "red");
            preparedStatement.setString(3, "shirt");
            preparedStatement.setString(4, "brand1");
            preparedStatement.setString(5, "m");
            preparedStatement.setString(6, "f1");
            preparedStatement.setInt(7, 10);
            preparedStatement.setFloat(8, 20);
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clothes clothes1 = new Shirt(20, "brand1", "m", "red", 1);
        assertEquals(10, homepagedao.checkAvailability(clothes1));
        homepagedao.updateAvailability(1, 20);
        assertEquals(20, homepagedao.checkAvailability(clothes1));
    }
}