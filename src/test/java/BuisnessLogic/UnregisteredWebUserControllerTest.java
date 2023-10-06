package BuisnessLogic;

import DataAccess.HomePageDAO;
import DomainModel.Clothes;
import DomainModel.RegisteredWebUser;
import DomainModel.UnregisteredWebUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UnregisteredWebUserControllerTest {

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
    void registerWebUser() {
        UnregisteredWebUser user = new UnregisteredWebUser();
        HomePageDAO homepage = new HomePageDAO("C:/sqlite/ShopOnline.db");
        UnregisteredWebUserController controller = new UnregisteredWebUserController(homepage);
        try {
            RegisteredWebUser registered = controller.registerWebUser("user1", "password1");
            System.out.println(registered.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            RegisteredWebUser registered = controller.registerWebUser("user1", "password1");
            if (registered == null)
                System.out.println("User already registered");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void searchByPrice(){
        HomePageDAO homepage = new HomePageDAO("C:/sqlite/ShopOnline.db");
        UnregisteredWebUserController controller = new UnregisteredWebUserController(homepage);
        String query = "INSERT INTO Clothes (color, category, brand, size,storageID, qty, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = homepage.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setInt(5, 1);
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 20);
            preparedStatement.executeUpdate();


            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setInt(5, 1);
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 90);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Clothes> results = controller.searchByPrice(60);
        for(Clothes it : results){
            System.out.println(it.getPrice());
        }
    }
}