package DataAccess;

import DomainModel.Clothes;

import java.sql.*;

public class SuperUserDAO {
    private Connection connection;

    public SuperUserDAO(String databaseURL) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean addNewClothes(Clothes clothes,String namefactory,int qnt){
        String selectQuery = "SELECT codStorage FROM Factory WHERE name = ?";
        int factoryCode=0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setString(1, namefactory);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                factoryCode = resultSet.getInt("codStorage");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String insertQuery = "INSERT INTO Clothes (color, category, brand, size,storageID, qty, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, clothes.getColor());
            preparedStatement.setString(2, clothes.getCategory());
            preparedStatement.setString(3, clothes.getBrand());
            preparedStatement.setString(4, clothes.getSize());
            preparedStatement.setInt(5,factoryCode); //this method set NULL storage id
            preparedStatement.setInt(6, qnt);
            preparedStatement.setFloat(7, clothes.getPrice());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //method with codstorage not null
    public boolean addNewClothes(Clothes clothes, int qnt){
        //prima di continuare qua devo recuperare il codice di storageID data una factory
        String insertQuery = "INSERT INTO Clothes (color, category, brand, size,storageID, qty, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, clothes.getColor());
            preparedStatement.setString(2, clothes.getCategory());
            preparedStatement.setString(3, clothes.getBrand());
            preparedStatement.setString(4, clothes.getSize());
            preparedStatement.setNull(5, Types.INTEGER); //this method set NULL storage id
            preparedStatement.setInt(6, qnt);
            preparedStatement.setFloat(7, clothes.getPrice());

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeClothes(int codclothes){
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

    public boolean updateQtyClothes(int codclothes, int newqnt){ //pass thw key because the super uces can access to db and read the information
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

    }


