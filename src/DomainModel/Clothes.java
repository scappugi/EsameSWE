package DomainModel;

public class Clothes {
    private float price;
    private String brand;
    private String size;
    private String color;
    private String category;

    public Clothes(float p, String b, String s, String co, String ca) {
        price = p;
        brand = b;
        size = s;
        color = co;
        category = ca;
    }

    public float getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getCategory() {
        return category;
    }
}
