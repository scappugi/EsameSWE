package BuisnessLogic;

import DomainModel.Shirt;
import DomainModel.Sweatshirt;
import DomainModel.Trousers;

public interface AbstractFactory {
    public Trousers createTrousers(int price, String size, String color);
    public Shirt createShirt();
    public Sweatshirt createSweatShirt();

}
