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
        if (flag)
            System.out.println("new user registered");
        else System.out.println("user already present");

        //test fail
        try {
            flag = homepagedao.registerUser("user1", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (flag)
            System.out.println("new user registered");
        else System.out.println("user already present");
    }

    @Test
    void login() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        boolean flag = false;
        RegisteredWebUser user = null;
        try {
            flag = homepagedao.registerUser("user1", "password");
            user = homepagedao.login("user1", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (flag)
            System.out.println("new user registered");
        else System.out.println("user already present");


        if (user != null)
            System.out.println("user logged");
        else System.out.println("user not found");
    }

    @Test
    void registerCreditCard() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        boolean flag = false;
        try {
            homepagedao.registerUser("user1", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DebitCard card = new DebitCard(1, 111, Date.valueOf(LocalDate.now()));
        flag = homepagedao.registerCreditCard(card, "user1");
        if (flag)
            System.out.println("new card registered");
        else System.out.println("card already present");
    }

    @Test
    void removeCreditCard() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        boolean flag = false;
        try {
            homepagedao.registerUser("user1", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DebitCard card = new DebitCard(1, 111, Date.valueOf(LocalDate.now()));
        homepagedao.registerCreditCard(card, "user1");
        flag = homepagedao.removeCreditCard(1, 111, "user1");
        if (flag)
            System.out.println("card removed");
        else System.out.println("card not found");
    }

    @Test
    void getAllDebitCards() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        boolean flag = false;
        try {
            homepagedao.registerUser("user1", "password");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        DebitCard card = new DebitCard(1, 111, Date.valueOf(LocalDate.now()));
        flag = homepagedao.registerCreditCard(card, "user1");
        if (flag)
            System.out.println("new card registered");
        else System.out.println("card already present");

        ArrayList<DebitCard> result = homepagedao.getAllDebitCards("user1");
        for(DebitCard it : result)
            it.show();
    }

    @Test
    void checkAvailability() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        Connection connection = homepagedao.getConnection();

        String query1 = "INSERT INTO Clothes(codClothes, COLOR, CATEGORY, BRAND, SIZE, STORAGEID, QTY, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "red");
            preparedStatement.setString(3, "shirt");
            preparedStatement.setString(4, "brand1");
            preparedStatement.setString(5, "m");
            preparedStatement.setInt(6,1);
            preparedStatement.setInt(7, 10);
            preparedStatement.setFloat(8, 20);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clothes clothes1 = new Shirt(20, "brand1", "m", "red", 1);
        System.out.println("qty : " + homepagedao.checkAvailability(clothes1));

    }

    @Test
    void updateAvailability() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        Connection connection = homepagedao.getConnection();

        String query1 = "INSERT INTO Clothes(codClothes, COLOR, CATEGORY, BRAND, SIZE, STORAGEID, QTY, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "red");
            preparedStatement.setString(3, "shirt");
            preparedStatement.setString(4, "brand1");
            preparedStatement.setString(5, "m");
            preparedStatement.setInt(6,1);
            preparedStatement.setInt(7, 10);
            preparedStatement.setFloat(8, 20);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clothes clothes1 = new Shirt(20, "brand1", "m", "red", 1);
        System.out.println("qty : " + homepagedao.checkAvailability(clothes1));
        homepagedao.updateAvailability(1, 2);
    }
}