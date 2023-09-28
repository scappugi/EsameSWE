package DataAccess;

import DomainModel.Clothes;
import DomainModel.Shirt;
import DomainModel.Sweatshirt;
import DomainModel.Trousers;
import DomainModel.WebUser;

import java.sql.*;
import java.util.ArrayList;

public class HomePageDAO {

    private Connection connection;

    public HomePageDAO(String databaseURL) { //C:/sqlite/ShopOnline.db
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(WebUser webUser){
        return true;
    }

    public boolean login(String username, String password){
        String query = "SELECT * FROM WebUser WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            // If there is a line in the result, it means the credentials are correct
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // If there is no match, return false
        return false;
    }

    public boolean registerCreditCard(/**/){
        return true;
    }

    public boolean checkAvailability(Clothes clothes){
        return true;
    }

    public boolean checkDescription(Clothes clothes){
        return true;
    }

    public ArrayList<Trousers> getTrousers(/*bisogna mettere la roba su cui filtrare(dal DB)*/){
        ArrayList<Trousers> trousers1 = null;
        return trousers1 ; //dovrà ritornare qualcosa di sensato
    }

    public ArrayList<Sweatshirt> getSweatshirt(){
        ArrayList<Sweatshirt> sweatshirts = null;
        return sweatshirts;
    }

    public ArrayList<Shirt> getShirt(){
        ArrayList<Shirt> shirts = null;
        return shirts;
    }





}
