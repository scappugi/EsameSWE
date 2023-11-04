package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.DataBase;
import DataAccess.HomePageDAO;
import DataAccess.PrivateAreaDAO;
import DomainModel.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


import static java.time.LocalDate.*;


class RegisteredWebUserControllerTest {

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

            String query = "INSERT INTO WebUser(userName, password) VALUES  (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "user1");
            preparedStatement.setString(2, "password");
            preparedStatement.executeUpdate();

            String query1 = "INSERT INTO Orders(codOrder, date, shipmentDate, user) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement7 = connection.prepareStatement(query1);
            preparedStatement7.setInt(1, 1);
            preparedStatement7.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement7.setDate(3, Date.valueOf(LocalDate.now().plusDays(3)));
            preparedStatement7.setString(4, "user1");
            preparedStatement7.executeUpdate();

            String query2 = "INSERT INTO Clothes(codClothes, COLOR, CATEGORY, BRAND, SIZE, factory, QTY, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement8 = connection.prepareStatement(query2);
            preparedStatement8.setInt(1, 1);
            preparedStatement8.setString(2, "red");
            preparedStatement8.setString(3, "shirt");
            preparedStatement8.setString(4, "brand1");
            preparedStatement8.setString(5, "m");
            preparedStatement8.setString(6, "f1");
            preparedStatement8.setInt(7, 10);
            preparedStatement8.setFloat(8, 20);
            preparedStatement8.executeUpdate();


            DataBase.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void accessPrivateArea() {
        HomePageDAO homepagedao = new HomePageDAO();
        CartDAO cartdao = new CartDAO(homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO();
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao, homepagedao, privateareadao);


        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        controller.accessPrivateArea();
        assertEquals(1, user.getPrivateArea().getOrders().size());
    }

    @Test
    void addClothesToCart() {
        HomePageDAO homepagedao = new HomePageDAO();
        CartDAO cartdao = new CartDAO(homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO();
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao, homepagedao, privateareadao);

        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);


        Shirt clothes = new Shirt(20, "brand1", "m", "red", 1);
        assertEquals(true, controller.addClothesToCart(clothes, 2));
        assertEquals(false, controller.addClothesToCart(clothes, 2));

    }

    @Test
    void removeClothesFromCart() {
        HomePageDAO homepagedao = new HomePageDAO();
        CartDAO cartdao = new CartDAO(homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO();
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao, homepagedao, privateareadao);


        //create user
        String query = "INSERT INTO WebUser(userName, password) VALUES  (?, ?)";
        Connection connection = null;
        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);


        Shirt clothes = new Shirt(20, "brand1", "m", "red", 1);
        assertEquals(true, controller.addClothesToCart(clothes, 2));
        assertEquals(true, controller.removeClothesFromCart(clothes));
        assertEquals(false, controller.removeClothesFromCart(clothes));

    }

    @Test
    void modifyQuantityClothesFromCart() {
        HomePageDAO homepagedao = new HomePageDAO();
        CartDAO cartdao = new CartDAO(homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO();
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao, homepagedao, privateareadao);


        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);


        Shirt clothes = new Shirt(20, "brand1", "m", "red", 1);
        assertEquals(true, controller.addClothesToCart(clothes, 2));
        assertEquals(true, controller.modifyQuantityClothesFromCart(clothes, 1));
        assertEquals(false, controller.modifyQuantityClothesFromCart(clothes, 80));

    }

    @Test
    void buyCart() {
        HomePageDAO homepagedao = new HomePageDAO();
        CartDAO cartdao = new CartDAO(homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO();
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao, homepagedao, privateareadao);

        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);


        Shirt clothes = new Shirt(20, "brand1", "m", "red", 1);
        assertEquals(true, controller.addClothesToCart(clothes, 2));
        assertEquals(true, controller.buyCart());

    }

    @Test
    void addDebitCard() {
        HomePageDAO homepagedao = new HomePageDAO();
        CartDAO cartdao = new CartDAO(homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO();
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao, homepagedao, privateareadao);


        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        assertEquals(true, controller.addCDebitCard(1, 111, Date.valueOf(LocalDate.now())));

    }

    @Test
    void removeDebitCard() {
        HomePageDAO homepagedao = new HomePageDAO();
        CartDAO cartdao = new CartDAO(homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO();
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao, homepagedao, privateareadao);


        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        assertEquals(true, controller.addCDebitCard(1, 111, Date.valueOf(LocalDate.now())));
        assertEquals(true, controller.removeDebitCard(1, 111));

    }

    @Test
    void getAllcards() {
        HomePageDAO homepagedao = new HomePageDAO();
        CartDAO cartdao = new CartDAO(homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO();
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao, homepagedao, privateareadao);


        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        assertEquals(true, controller.addCDebitCard(1, 111, Date.valueOf(LocalDate.now())));
        ArrayList<DebitCard> result = controller.getAllCard();
        assertNotNull(result);

    }

}