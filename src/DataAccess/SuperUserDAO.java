package DataAccess;

import DomainModel.Clothes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuperUserDAO {
    private Connection connection;

    public SuperUserDAO(String databaseURL) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean addNewClothes(Clothes clothes,String codstorage,int qnt){
        return true;
    } //method with codstorage not null

    public boolean addNewClothes(Clothes clothes, int qnt){
        //prima di continuare qua devo recuperare il codice di storageID data una factory
        String insertQuery = "INSERT INTO Clothes (color, category, brand, size,storageID, qty, price) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, clothes.getColor());
            preparedStatement.setString(2, clothes.getCategory());
            preparedStatement.setString(3, clothes.getBrand());
            preparedStatement.setString(4, clothes.getSize());
            preparedStatement.setInt(5, qnt);

            int rowsInserted = preparedStatement.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeClothes(Clothes clothes){
        return true;
    }

    public boolean updateClothes(Clothes clothes){
        return true;
    }


}
