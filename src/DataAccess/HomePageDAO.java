package DataAccess;

import DomainModel.*;

import java.sql.*;
import java.util.ArrayList;

public class HomePageDAO {

    private Connection connection;

    public HomePageDAO(String databaseURL) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean registerUser(String username, String password) throws SQLException {
        if (!login(username, password)) {
            String query = "INSERT INTO WebUser (UserName, Password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // insert element into db
            int rowsAffected = preparedStatement.executeUpdate();

            // close delcaration
            preparedStatement.close();

            // Se è stato inserito almeno un record, la registrazione ha successo
            if (rowsAffected > 0)
                return true;

            else return false;
        }
        return false; // the user is already registred
    }

    public boolean login(String username, String password) {
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

    public boolean registerCreditCard(DebitCard debitcard, String username) throws SQLException {
        int codUser = 0;
        String selectQuery = "SELECT * FROM WebUser WHERE userName = ?"; //ricerca user
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            // Leggi i dati dell'utente dal ResultSet e crea un oggetto WebUser
            codUser = resultSet.getInt("codUser"); //una volta trovato l' user inserisce la carta
            String insertQuery = "INSERT INTO DebitCard (codCard, CVV, Date, UserID) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
            preparedStatement1.setInt(1, debitcard.getCodCard());
            preparedStatement1.setInt(2, debitcard.getCVV());
            preparedStatement1.setDate(3, (Date) debitcard.getDate());
            preparedStatement1.setInt(4, codUser);

            int rowsInserted = preparedStatement1.executeUpdate();
            connection.commit();

            return rowsInserted > 0; //se aggiungo almeno una riga ritorno true
        }
        return false;
    }

    //search if the qnt is not 0
    public int checkAvailability(Clothes clothes) {
        int qty = 0;

        String selectQuery = "SELECT * FROM Clothes WHERE codClothes = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, clothes.getCodclothes());

            ResultSet resultSet = preparedStatement.executeQuery();
            qty = resultSet.getInt("qty");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qty;
    }

    public void updateAvailability(Clothes clothes, int newqty) {
        String query = "UPDATE Clothes SET qty = ? WHERE codClothes = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, newqty);
            preparedStatement.setInt(2, clothes.getCodclothes());
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkDescription(Clothes clothes) {
        return true;
    }

    public ArrayList<Trousers> getTrousers(/*bisogna mettere la roba su cui filtrare(dal DB)*/) {
        ArrayList<Trousers> trousers1 = null;
        return trousers1; //dovrà ritornare qualcosa di sensato
    }

    public ArrayList<Sweatshirt> getSweatshirt() {
        ArrayList<Sweatshirt> sweatshirts = null;
        return sweatshirts;
    }

    public ArrayList<Shirt> getShirt() {
        ArrayList<Shirt> shirts = null;
        return shirts;
    }


}
