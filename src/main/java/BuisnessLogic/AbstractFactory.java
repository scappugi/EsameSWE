package BuisnessLogic;

import DomainModel.Shirt;
import DomainModel.Sweatshirt;
import DomainModel.Trousers;

public interface AbstractFactory {
    public Trousers createTrousers(String size, String color, int code);
    public Shirt createShirt(String size, String color, int code);
    public Sweatshirt createSweatShirt(String size, String color, int code);
    public String getName();
}
