package DomainModel;


public class Shirt extends Clothes{

   /* public Shirt(float price, String brand, String size, String color){
        super(price, brand, size, color, "shirt");
    }*/

    public Shirt(float price, String brand, String size, String color, int code){
        super(price, brand, size, color, "shirt", code);
    }
}
