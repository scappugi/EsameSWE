package DataAccess;

import DomainModel.Order;
import DomainModel.PrivateArea;

import java.util.ArrayList;

public class PrivateAreaDAO {

    public PrivateAreaDAO(){}

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

    public void popolatePrivateArea(PrivateArea privateArea) {
    }
}
