package DataAccess;

import DomainModel.*;

import java.sql.*;
import java.util.ArrayList;

public class HomePageDAO {

    public HomePageDAO() {
    }


    public boolean registerUser(String username, String password) throws SQLException {
        Connection connection = DataBase.getConnection();
        String query = "INSERT INTO WebUser (UserName, Password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
        DataBase.closeConnection(connection);
        return true;
    }

    public RegisteredWebUser login(String username, String password) {
        String query = "SELECT * FROM WebUser WHERE username = ? AND password = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            // If there is a line in the result, it means the credentials are correct
            if (resultSet.next()) {
                DataBase.closeConnection(connection);
                return new RegisteredWebUser(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // If there is no match, return false
        return null;
    }

    public boolean registerCreditCard(DebitCard debitcard, String username) {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = DataBase.getConnection();
            String insertQuery = "INSERT INTO DebitCard (codCard, CVV, Date, user) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertQuery);
            preparedStatement1.setInt(1, debitcard.getCodCard());
            preparedStatement1.setInt(2, debitcard.getCVV());
            preparedStatement1.setDate(3, (Date) debitcard.getDate());
            preparedStatement1.setString(4, username);
            preparedStatement1.executeUpdate();
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public boolean removeCreditCard(int cardNumber, int CVV, String username) {
        try {
            Connection connection = DataBase.getConnection();
            String deleteQuery = "DELETE FROM DebitCard WHERE codCard = ? AND CVV = ? AND user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, cardNumber);
            preparedStatement.setInt(2, CVV);
            preparedStatement.setString(3, username);
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public ArrayList<DebitCard> getAllDebitCards(String username) {
        ArrayList<DebitCard> debitCards = new ArrayList<>();

        try {
            Connection connection = DataBase.getConnection();
            String selectQuery = "SELECT * FROM DebitCard WHERE user = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int cardNumber = resultSet.getInt("codCard");
                int cvv = resultSet.getInt("CVV");
                Date cardDate = resultSet.getDate("date");

                DebitCard debitCard = new DebitCard(cardNumber, cvv, cardDate);
                debitCards.add(debitCard);
            }
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debitCards;
    }


    //search if the qnt is not 0
    public int checkAvailability(Clothes clothes) {
        int qty = 0;
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        String selectQuery = "SELECT * FROM Clothes WHERE codClothes = ?";
        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, clothes.getCodclothes());

            ResultSet resultSet = preparedStatement.executeQuery();
            qty = resultSet.getInt("qty");
            DataBase.closeConnection(connection);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qty;
    }

    public void updateAvailability(int codclothes, int newqty) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String query = "UPDATE Clothes SET qty = ? WHERE codClothes = ?";

        try {
            connection = DataBase.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newqty);
            preparedStatement.setInt(2, codclothes);
            preparedStatement.executeUpdate();
            DataBase.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
