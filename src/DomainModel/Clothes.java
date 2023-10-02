package DomainModel;

public class Clothes {

    private int codclothes;
    private float price;
    private String brand;
    private String size;
    private String color;
    private String category;

    public Clothes(float p, String b, String s, String co, String ca, int cc) {
        price = p;
        brand = b;
        size = s;
        color = co;
        category = ca;
        codclothes = cc;
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
    public int getCodclothes() {
        return codclothes;
    }

}
