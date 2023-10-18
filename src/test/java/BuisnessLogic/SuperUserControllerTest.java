package BuisnessLogic;

import DataAccess.DataBase;
import DataAccess.SuperUserDAO;
import DomainModel.SuperUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SuperUserControllerTest {

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
    void addFactory() {
        SuperUserDAO superdao = new SuperUserDAO();
        SuperUser superuser = SuperUser.getInstance("super", "password");
        SuperUserController controller = new SuperUserController(superuser, superdao);

        assertEquals(true, controller.addFactory("f4"));
    }

    @Test
    void removeFactory() {
        SuperUserDAO superdao = new SuperUserDAO();
        SuperUser superuser = SuperUser.getInstance("super", "password");
        SuperUserController controller = new SuperUserController(superuser, superdao);

        assertEquals(true, controller.removeFactory("f3"));
    }

    @Test
    void addNewClothes() {
        SuperUserDAO superdao = new SuperUserDAO();
        SuperUser superuser = SuperUser.getInstance("super", "password");
        SuperUserController controller = new SuperUserController(superuser, superdao);

        assertEquals(true, controller.addNewClothes("shirt", "m", "red", 20, "f1", 1));
        assertEquals(false, controller.addNewClothes("shirt", "m", "red", 20, "f5", 3));
    }

    @Test
    void modifyExistingClothes() {
        SuperUserDAO superdao = new SuperUserDAO();
        SuperUser superuser = SuperUser.getInstance("super", "password");
        SuperUserController controller = new SuperUserController(superuser, superdao);

        assertEquals(true, controller.addNewClothes("shirt", "m", "red", 20, "f1", 1));
        assertEquals(true, controller.modifyExistingClothes(1, 10));
        assertEquals(false, controller.modifyExistingClothes(5, 10));
    }

    @Test
    void deleteExistingClothes() {
        SuperUserDAO superdao = new SuperUserDAO();
        SuperUser superuser = SuperUser.getInstance("super", "password");
        SuperUserController controller = new SuperUserController(superuser, superdao);

        assertEquals(true, controller.addNewClothes("shirt", "m", "red", 20, "f1", 1));
        assertEquals(true, controller.deleteExistingClothes(1));
        assertEquals(false, controller.deleteExistingClothes(5));
    }
}