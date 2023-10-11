package DataAccess;

import DomainModel.Order;
import DomainModel.PrivateArea;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PrivateAreaDAOTest {

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
    void populatePrivateArea() {
        PrivateAreaDAO privatedao = new PrivateAreaDAO("C:/sqlite/ShopOnline.db");

        //create user
        String query = "INSERT INTO WebUser(userName, password) VALUES  (?, ?)";
        Connection connection = privatedao.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "user1");
            preparedStatement.setString(2, "password");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //create order
        String query1 = "INSERT INTO Orders(codOrder, date, shipmentDate, userID) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query1);
            preparedStatement.setInt(1, 1);
            preparedStatement.setDate(2, Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now().plusDays(3)));
            preparedStatement.setInt(4, 6);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrivateArea privatearea = new PrivateArea();
        privatedao.populatePrivateArea(privatearea, "user1");
        assertEquals(1, privatearea.getOrders().size());
        /*for (Order it : privatearea.getOrders())
            it.show();*/
    }
}