package DomainModel;

import java.util.ArrayList;

public class PrivateArea {

    private ArrayList<Order> orders;
    private WebUser owner;

    public PrivateArea(){
        orders = new ArrayList<>();
    }
    public void addorder(Order neworder){
        orders.add(neworder);
    }
    public void showitems(){
        for(int i = 0; i < orders.size(); i++){
            orders.get(i).show();
        }
    }
}
