package DataAccess;

import BuisnessLogic.AbstractFactory;
import DomainModel.Clothes;

import java.sql.*;
import java.util.ArrayList;

public class SuperUserDAO {
    private Connection connection;

    public SuperUserDAO(String databaseURL) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addNewClothes(Clothes clothes, String namefactory, int qnt) {
        String selectQuery = "SELECT codStorage FROM Factory WHERE name = ?";
        int factoryCode = -1;
        int clothescode = -1;
        String selectQuery1 = "SELECT codClothes FROM Clothes WHERE codClothes = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, namefactory);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                factoryCode = resultSet.getInt("codStorage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery1)) {
            preparedStatement.setInt(1, clothes.getCodclothes());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                clothescode = resultSet.getInt("codClothes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (factoryCode != -1 && clothescode == -1) {
            String insertQuery = "INSERT INTO Clothes (codClothes, color, category, brand, size,storageID, qty, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, clothes.getCodclothes());
                preparedStatement.setString(2, clothes.getColor());
                preparedStatement.setString(3, clothes.getCategory());
                preparedStatement.setString(4, clothes.getBrand());
                preparedStatement.setString(5, clothes.getSize());
                preparedStatement.setInt(6, factoryCode); //this method set NULL storage id
                preparedStatement.setInt(7, qnt);
                preparedStatement.setFloat(8, clothes.getPrice());

                int rowsInserted = preparedStatement.executeUpdate();

                return rowsInserted > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean removeClothes(int codclothes) {
        String deleteQuery = "DELETE FROM Clothes WHERE codClothes = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, codclothes);

            int rowsDeleted = preparedStatement.executeUpdate();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateQtyClothes(int codclothes, int newqnt) { //pass thw key because the superuser can access to db and read the information
        String updateQuery = "UPDATE Clothes SET qty = ? WHERE codClothes = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, newqnt);
            preparedStatement.setInt(2, codclothes);

            int rowsUpdated = preparedStatement.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int getFactoryIdByName(String name) {
        String selectQuery = "SELECT codStorage FROM Factory WHERE name = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("codStorage");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }

    public boolean addFactory(String name) {
        if (getFactoryIdByName(name) == -1) {
            String query = "INSERT INTO Factory (name) VALUES (?)";
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);

                // insert element into db
                int rowsAffected = preparedStatement.executeUpdate();

                // close declaration
                preparedStatement.close();

                // Se è stato inserito almeno un record, la registrazione ha successo
                if (rowsAffected > 0)
                    return true;

                else return false;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false; // the user is already registred
    }

    public boolean removeFactory(String name) {
        if (getFactoryIdByName(name) != -1) {
            String query = "DELETE FROM Factory WHERE name = ?";
            try {
                PreparedStatement preparedstatement = connection.prepareStatement(query);
                preparedstatement.setString(1, name);

                int rowsDeleted = preparedstatement.executeUpdate();

                return rowsDeleted > 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

}


