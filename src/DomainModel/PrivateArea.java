package DomainModel;

import java.util.ArrayList;

public class PrivateArea {

    private ArrayList<Order> orders;
    private RegisteredWebUser owner;

    public PrivateArea(){
        orders = new ArrayList<>();
    }

    public ArrayList<Order> getOrders(){
        return orders;
    }

}
