package DomainModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private Date ordered; //when the order was placed
    private Date shipment; //when the order was shipped
    private String shipto;
    private Map<Clothes, Integer> items = new HashMap<>();

    public Order(int id, Date ordered, Date shipment, String shipto, Map<Clothes, Integer> items) {
        this.id = id;
        this.ordered = ordered;
        this.shipment = shipment;
        this.shipto = shipto;
        this.items = items;
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
