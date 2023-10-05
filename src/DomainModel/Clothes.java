package DomainModel;

public abstract class Clothes {

    private int codclothes;
    private float price;
    private String brand;
    private String size;
    private String color;
    private String category;

    public Clothes(float price, String brand, String size, String color, String category, int codclothes) {
        this.price = price;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.category = category;
        this.codclothes = codclothes;
    }
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
    public int getCodclothes() {
        return codclothes;
    }

}
