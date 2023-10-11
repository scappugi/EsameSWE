package BuisnessLogic;

import DataAccess.CartDAO;
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
    void accessPrivateArea() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        CartDAO cartdao = new CartDAO("C:/sqlite/ShopOnline.db", homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO("C:/sqlite/ShopOnline.db");
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao,homepagedao, privateareadao);


        //create user
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
        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);
        //create order
        String query1 = "INSERT INTO Orders(codOrder, date, shipmentDate, userID) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, 1);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now().plusDays(3)));
            preparedStatement.setInt(4, 25);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        controller.accessPrivateArea();
        assertEquals(1,user.getPrivateArea().getOrders().size());
        /*for(Order it : user.getPrivateArea().getOrders())
            it.show();*/
    }

    @Test
    void addClothesToCart() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        CartDAO cartdao = new CartDAO("C:/sqlite/ShopOnline.db", homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO("C:/sqlite/ShopOnline.db");
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao,homepagedao, privateareadao);


        //create user
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
        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        //create clothes
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

        Shirt clothes = new Shirt(20,"brand1", "m", "red", 1);
        assertEquals(true,controller.addClothesToCart(clothes, 2));
        assertEquals(false,controller.addClothesToCart(clothes, 2));
    }

    @Test
    void removeClothesFromCart() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        CartDAO cartdao = new CartDAO("C:/sqlite/ShopOnline.db", homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO("C:/sqlite/ShopOnline.db");
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao,homepagedao, privateareadao);


        //create user
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
        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        //create clothes
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

        Shirt clothes = new Shirt(20,"brand1", "m", "red", 1);
        assertEquals(true,controller.addClothesToCart(clothes, 2));
        assertEquals(true, controller.removeClothesFromCart(clothes));
        assertEquals(false, controller.removeClothesFromCart(clothes));
    }

    @Test
    void modifyQuantityClothesFromCart() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        CartDAO cartdao = new CartDAO("C:/sqlite/ShopOnline.db", homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO("C:/sqlite/ShopOnline.db");
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao,homepagedao, privateareadao);


        //create user
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
        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        //create clothes
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

        Shirt clothes = new Shirt(20,"brand1", "m", "red", 1);
        assertEquals(true,controller.addClothesToCart(clothes, 2));
        assertEquals(true,controller.modifyQuantityClothesFromCart(clothes,1));
        assertEquals(false,controller.modifyQuantityClothesFromCart(clothes,80));

    }

    @Test
    void buyCart() {
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        CartDAO cartdao = new CartDAO("C:/sqlite/ShopOnline.db", homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO("C:/sqlite/ShopOnline.db");
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao,homepagedao, privateareadao);


        //create user
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
        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        //create clothes
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

        Shirt clothes = new Shirt(20,"brand1", "m", "red", 1);
        assertEquals(true,controller.addClothesToCart(clothes, 2));
        assertEquals(true,controller.buyCart());
    }

    @Test
    void addDebitCard(){
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        CartDAO cartdao = new CartDAO("C:/sqlite/ShopOnline.db", homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO("C:/sqlite/ShopOnline.db");
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao,homepagedao, privateareadao);


        //create user
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
        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        assertEquals(true,controller.addCDebitCard(1,111, Date.valueOf(LocalDate.now())));
    }

    @Test
    void removeDebitCard(){
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        CartDAO cartdao = new CartDAO("C:/sqlite/ShopOnline.db", homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO("C:/sqlite/ShopOnline.db");
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao,homepagedao, privateareadao);


        //create user
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
        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        assertEquals(true,controller.addCDebitCard(1,111, Date.valueOf(LocalDate.now())));
        assertEquals(true,controller.removeDebitCard(1, 111));

    }

    @Test
    void getAllcards(){
        HomePageDAO homepagedao = new HomePageDAO("C:/sqlite/ShopOnline.db");
        CartDAO cartdao = new CartDAO("C:/sqlite/ShopOnline.db", homepagedao);
        PrivateAreaDAO privateareadao = new PrivateAreaDAO("C:/sqlite/ShopOnline.db");
        RegisteredWebUserController controller = new RegisteredWebUserController(cartdao,homepagedao, privateareadao);


        //create user
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
        RegisteredWebUser user = controller.login("user1", "password");
        controller.setRegisteredwebuser(user);

        assertEquals(true,controller.addCDebitCard(1,111, Date.valueOf(LocalDate.now())));
        ArrayList<DebitCard> result = controller.getAllCard();
        assertNotNull(result);
        /*for(DebitCard it : result)
            it.show();*/
    }

}