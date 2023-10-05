package DomainModel.Search;

import DomainModel.Clothes;
import DomainModel.Shirt;

import java.sql.*;
import java.util.ArrayList;

public class SearchConcrete implements Search {
    protected Connection connection;
    public SearchConcrete(String databaseURL){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ArrayList<Clothes> searchClothes() {


        ArrayList<Clothes> results = new ArrayList<>();

        try {
           // Class.forName("com.mysql.cj.jdbc.Driver");
            Statement statement = connection.createStatement(); //to execute query
            String query = "SELECT * FROM Clothes";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) { //loop to see all the results
                float price = resultSet.getInt("price");
                String brand = resultSet.getString("brand");
                String size = resultSet.getString("size");
                String color = resultSet.getString("color");
                String category = resultSet.getString("category");


                if (category.equals("shirt")) { //look what category is clothes
                    Clothes cl = new Shirt(price, brand, size, color);
                    results.add(cl);
                } else if (category.equals("trousers")) {
                    Clothes cl = new Shirt(price, brand, size, color);
                    results.add(cl);
                } else if (category.equals("sweatshirt")) {
                    Clothes cl = new Shirt(price, brand, size, color);
                    results.add(cl);
                }

            }
            connection.close(); // close connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
