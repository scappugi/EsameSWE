package DomainModel;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String id;
    private Date ordered; //when the order was placed
    private Date shipment; //when the order was shipped
    private String shipto;
    private Cart cart;

    public Order(String id, Date ordered, Date shipment, String shipto) {
        this.id = id;
        this.ordered = ordered;
        this.shipment = shipment;
        this.shipto = shipto;
    }

    public void setShipped(Date date){
        shipment = date;
    }

    public void show(){
        System.out.println(("id : " + id));
        System.out.println(("ordered : " + ordered));
        System.out.println(("shipped : " + shipment));
        System.out.println(("ship to : " + shipto));
    }

}
