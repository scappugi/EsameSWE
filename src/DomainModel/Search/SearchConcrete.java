package DomainModel.Search;

import DomainModel.Clothes;

import java.sql.*;
import java.util.ArrayList;

public class SearchConcrete implements Search {
    @Override
    public ArrayList<Clothes> searchClothes() throws ClassNotFoundException {
        /*Connection connection = null;
        PreparedStatement preparedstatement = null;
        ResultSet resultset = null;
        return null;*/
        String jdbcUrl = "jdbc:mysql://localhost:5432/ShopOnline";
        String username = "postgres";
        String password = "fiorentina";

        ArrayList<Clothes> results = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password); //connection to db

            Statement statement = connection.createStatement(); //to execute query
            String query = "SELECT * FROM Clothes";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) { //loop to see all the results
                float price = resultSet.getInt("price");
                String brand = resultSet.getString("brand");
                String size = resultSet.getString("size");
                String color = resultSet.getString("color");
                String category = resultSet.getString("category");

                Clothes cl = new Clothes(price, brand,size, color, category);
                results.add(cl);

            }
            connection.close(); // close connection
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
