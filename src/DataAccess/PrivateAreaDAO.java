package DataAccess;

import DomainModel.Order;
import DomainModel.PrivateArea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrivateAreaDAO {

    public PrivateAreaDAO(){}

    private Connection connection;

    public PrivateAreaDAO(String databaseURL) {
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

    //manca il modifyDaty() non so a cosa serve
    public ArrayList<Order> getAllOrder(){
        ArrayList<Order> orders = null;
        return orders;
    }
    public Order getOrder(Order o){
        return o;
    }

    public boolean addOrder(){
        return true;
    }

    public void popolatePrivateArea(PrivateArea privatearea) {

    }
}
