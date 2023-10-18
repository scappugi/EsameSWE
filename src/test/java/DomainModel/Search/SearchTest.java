package DomainModel.Search;

import BuisnessLogic.UnregisteredWebUserController;
import DataAccess.DataBase;
import DataAccess.HomePageDAO;
import DomainModel.Clothes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SearchTest {

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
    void searchBase() {
        String query = "INSERT INTO Clothes (color, category, brand, size,factory, qty, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setString(5, "f1");
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 20);
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setString(5, "f1");
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 90);
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SearchConcrete base = new SearchConcrete("C:/sqlite/ShopOnline.db");
        ArrayList<Clothes> result = base.searchClothes();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("red", result.get(0).getColor());
        assertEquals("shirt", result.get(0).getCategory());
        assertEquals("brand1", result.get(0).getBrand());
        assertEquals("m", result.get(0).getSize());
        assertEquals(20, result.get(0).getPrice());
    }


    @Test
    void searchByPrice() {
        String query = "INSERT INTO Clothes (color, category, brand, size,factory, qty, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setString(5, "f1");
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 20);
            preparedStatement.executeUpdate();


            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setString(5, "f1");
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 90);
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SearchConcrete base = new SearchConcrete("C:/sqlite/ShopOnline.db");
        DecoratorSearchPrice byprice = new DecoratorSearchPrice(base, 60);
        ArrayList<Clothes> result = byprice.searchClothes();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(20, result.get(0).getPrice());
    }

    @Test
    void searchBySize() {
        String query = "INSERT INTO Clothes (color, category, brand, size,factory, qty, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setString(5, "f1");
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 20);
            preparedStatement.executeUpdate();


            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setString(5, "f1");
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 90);
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SearchConcrete base = new SearchConcrete("C:/sqlite/ShopOnline.db");
        DecoratorSearchSize bysize = new DecoratorSearchSize(base, "m");
        ArrayList<Clothes> result = bysize.searchClothes();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("m", result.get(0).getSize());
        assertEquals("m", result.get(1).getSize());

    }

    @Test
    void searchByBrand() {
        String query = "INSERT INTO Clothes (color, category, brand, size,factory, qty, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DataBase.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setString(5, "f1");
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 20);
            preparedStatement.executeUpdate();


            preparedStatement.setString(1, "red");
            preparedStatement.setString(2, "shirt");
            preparedStatement.setString(3, "brand1");
            preparedStatement.setString(4, "m");
            preparedStatement.setString(5, "f1");
            preparedStatement.setInt(6, 10);
            preparedStatement.setFloat(7, 90);
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        SearchConcrete base = new SearchConcrete("C:/sqlite/ShopOnline.db");
        DecoratorSearchBrand bybrand = new DecoratorSearchBrand(base, "brand1");
        ArrayList<Clothes> result = bybrand.searchClothes();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("brand1", result.get(0).getBrand());
        assertEquals("brand1", result.get(1).getBrand());
    }

}