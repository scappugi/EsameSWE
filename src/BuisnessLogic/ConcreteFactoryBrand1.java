package BuisnessLogic;

import DomainModel.Shirt;
import DomainModel.Sweatshirt;
import DomainModel.Trousers;

public class ConcreteFactoryBrand1 implements AbstractFactory {


    @Override
    public Trousers createTrousers(int price, String size, String color) {
        return null;
    }

    @Override
    public Shirt createShirt() {
        return false;
    }

    @Override
    public Sweatshirt createSweatShirt() {
        return false;
    }
}
