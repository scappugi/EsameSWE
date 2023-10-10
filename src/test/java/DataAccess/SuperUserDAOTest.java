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
    void addNewClothes() {
        SuperUserDAO superdao = new SuperUserDAO("C:/sqlite/ShopOnline.db");
        Clothes clothes = new Shirt(30, "brand1", "m", "red", 1);
        Clothes clothes2 = new Trousers(50, "brand1", "m", "red", 2);

        String query = "INSERT INTO Factory (name) VALUES (?)";
        Connection connection = superdao.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "f1");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(superdao.addNewClothes(clothes, "f1", 10))
            System.out.println("add new clothes");
        else System.out.println("error");
        if(superdao.addNewClothes(clothes2, "f1", 20))
            System.out.println("add new clothes");
        else System.out.println("error");
        if(superdao.addNewClothes(clothes, "f2", 10))
            System.out.println("add new clothes");
        else System.out.println("error");
    }

    @Test
    void removeClothes() {
        SuperUserDAO superdao = new SuperUserDAO("C:/sqlite/ShopOnline.db");
        Clothes clothes = new Shirt(30, "brand1", "m", "red", 1);
        Clothes clothes2 = new Trousers(50, "brand1", "m", "red", 2);

        String query = "INSERT INTO Factory (name) VALUES (?)";
        Connection connection = superdao.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "f1");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(superdao.addNewClothes(clothes, "f1", 10))
            System.out.println("add new clothes");
        else System.out.println("error");
        if(superdao.addNewClothes(clothes2, "f1", 20))
            System.out.println("add new clothes");
        else System.out.println("error");

        if(superdao.removeClothes(1))
            System.out.println("clothes removed");
        else System.out.println("error");
        if(superdao.removeClothes(1))
            System.out.println("clothes removed");
        else System.out.println("error");
    }

    @Test
    void updateQtyClothes() {
        SuperUserDAO superdao = new SuperUserDAO("C:/sqlite/ShopOnline.db");
        Clothes clothes = new Shirt(30, "brand1", "m", "red", 1);
        Clothes clothes2 = new Trousers(50, "brand1", "m", "red", 2);

        String query = "INSERT INTO Factory (name) VALUES (?)";
        Connection connection = superdao.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "f1");
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(superdao.addNewClothes(clothes, "f1", 10))
            System.out.println("add new clothes");
        else System.out.println("error");
        if(superdao.addNewClothes(clothes2, "f1", 20))
            System.out.println("add new clothes");
        else System.out.println("error");

        if(superdao.updateQtyClothes(1, 55))
            System.out.println("qty updated");
        else System.out.println("error");
        if(superdao.updateQtyClothes(5, 55))
            System.out.println("qty updated");
        else System.out.println("error");
    }

    @Test
    void addFactory() {
        SuperUserDAO superdao = new SuperUserDAO("C:/sqlite/ShopOnline.db");
        if(superdao.addFactory("f1"))
            System.out.println("factory added");
        else System.out.println("error");
        if(superdao.addFactory("f1"))
            System.out.println("factory added");
        else System.out.println("error");
    }

    @Test
    void removeFactory() {
        SuperUserDAO superdao = new SuperUserDAO("C:/sqlite/ShopOnline.db");
        if(superdao.addFactory("f1"))
            System.out.println("factory added");
        else System.out.println("error");
        if(superdao.addFactory("f2"))
            System.out.println("factory added");
        else System.out.println("error");

        if(superdao.removeFactory("f1"))
            System.out.println("factory removed");
        else System.out.println("error");
        if(superdao.removeFactory("f1"))
            System.out.println("factory removed");
        else System.out.println("error");
    }
}