package DataAccess;

import DomainModel.Clothes;
import DomainModel.Shirt;
import DomainModel.Trousers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SuperUserDAOTest {

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

            String query = "INSERT INTO Factory (name) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "f1");
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void addNewClothes() {
        SuperUserDAO superdao = new SuperUserDAO();
        Clothes clothes = new Shirt(30, "brand1", "m", "red", 1);
        Clothes clothes2 = new Trousers(50, "brand1", "m", "red", 2);


        assertEquals(true, superdao.addNewClothes(clothes, "f1", 10));
        assertEquals(true, superdao.addNewClothes(clothes2, "f1", 20));
        assertEquals(false, superdao.addNewClothes(clothes, "f2", 10));
    }

    @Test
    void removeClothes() {
        SuperUserDAO superdao = new SuperUserDAO();
        Clothes clothes = new Shirt(30, "brand1", "m", "red", 1);
        Clothes clothes2 = new Trousers(50, "brand1", "m", "red", 2);


        assertEquals(true, superdao.addNewClothes(clothes, "f1", 10));
        assertEquals(true, superdao.addNewClothes(clothes2, "f1", 20));
        assertEquals(true, superdao.removeClothes(1));
        assertEquals(false, superdao.removeClothes(1));

    }

    @Test
    void updateQtyClothes() {
        SuperUserDAO superdao = new SuperUserDAO();
        Clothes clothes = new Shirt(30, "brand1", "m", "red", 1);
        Clothes clothes2 = new Trousers(50, "brand1", "m", "red", 2);


        assertEquals(true, superdao.addNewClothes(clothes, "f1", 10));
        assertEquals(true, superdao.addNewClothes(clothes2, "f1", 20));
        assertEquals(true, superdao.updateQtyClothes(1, 55));
        assertEquals(false, superdao.updateQtyClothes(5, 55));

    }

    @Test
    void addFactory() {
        SuperUserDAO superdao = new SuperUserDAO();
        String query = "INSERT INTO Factory (name) VALUES (?)";

        assertEquals(true, superdao.addFactory("f6"));
    }

    @Test
    void removeFactory() {
        SuperUserDAO superdao = new SuperUserDAO();

        assertEquals(true, superdao.addFactory("f2"));
        assertEquals(true, superdao.removeFactory("f1"));

    }
}