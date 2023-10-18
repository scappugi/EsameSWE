package DataAccess;

import BuisnessLogic.AbstractFactory;
import DomainModel.Clothes;

import java.sql.*;
import java.util.ArrayList;

public class SuperUserDAO {
    private Connection connection;

    public SuperUserDAO() {
    }

    public boolean addNewClothes(Clothes clothes, String factoryname, int qnt) {
        int clothescode = -1;
        PreparedStatement preparedStatement = null;
        String selectQuery = "SELECT codClothes FROM Clothes WHERE codClothes = ?";

        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, clothes.getCodclothes());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                clothescode = resultSet.getInt("codClothes");
            }
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (clothescode == -1) {
            String insertQuery = "INSERT INTO Clothes (codClothes, color, category, brand, size,factory, qty, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try {
                connection = DataBase.getConnection();
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setInt(1, clothes.getCodclothes());
                preparedStatement.setString(2, clothes.getColor());
                preparedStatement.setString(3, clothes.getCategory());
                preparedStatement.setString(4, clothes.getBrand());
                preparedStatement.setString(5, clothes.getSize());
                preparedStatement.setString(6, factoryname);
                preparedStatement.setInt(7, qnt);
                preparedStatement.setFloat(8, clothes.getPrice());
                int rows = preparedStatement.executeUpdate();
                DataBase.closeConnection(connection);
                return rows > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean removeClothes(int codclothes) {
        String deleteQuery = "DELETE FROM Clothes WHERE codClothes = ?";
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, codclothes);
            int rows = preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateQtyClothes(int codclothes, int newqnt) { //pass thw key because the superuser can access to db and read the information
        String updateQuery = "UPDATE Clothes SET qty = ? WHERE codClothes = ?";
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, newqnt);
            preparedStatement.setInt(2, codclothes);
            int rows = preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFactory(String name) {

        String query = "INSERT INTO Factory (name) VALUES (?)";
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean removeFactory(String name) {

        String query = "DELETE FROM Factory WHERE name = ?";
        try {
            connection = DataBase.getConnection();
            PreparedStatement preparedstatement = connection.prepareStatement(query);
            preparedstatement.setString(1, name);
            preparedstatement.executeUpdate();
            DataBase.closeConnection(connection);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}


