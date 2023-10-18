package BuisnessLogic;

import DataAccess.DataBase;
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
    void registerWebUser() {
        UnregisteredWebUser user = new UnregisteredWebUser();
        HomePageDAO homepage = new HomePageDAO();
        UnregisteredWebUserController controller = new UnregisteredWebUserController(homepage);

        try {
            assertNotNull(controller.registerWebUser("user1", "password1"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}