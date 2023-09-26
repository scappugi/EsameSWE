package DomainModel;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private String id;
    private Date ordered; //when the order was placed
    private Date shipped; //when the order was shipped
    private String shipto;
    private String status;
    private Cart cart;

    public Order(String id, Date o, String sh, String s) {
        this.id = id;
        ordered = o;
        shipped = null;
        shipto = sh;
        status = s;
    }

    public void setShipped(Date date){
        shipped = date;
    }

    public void show(){
        System.out.println(("id : " + id));
        System.out.println(("ordered : " + ordered));
        System.out.println(("shipped : " + shipped));
        System.out.println(("ship to : " + shipto));
        System.out.println(("status : " + status));
    }

}
