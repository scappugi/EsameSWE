package BuisnessLogic;

import DomainModel.Shirt;
import DomainModel.Sweatshirt;
import DomainModel.Trousers;

import java.util.Random;

public class ConcreteFactoryBrand1 implements AbstractFactory {
    private String name;
    public String getName() {
        return name;
    }

    public ConcreteFactoryBrand1(String n){
            this.name = n;
    }
    @Override
    public Trousers createTrousers(String size, String color) {
        //generate a casual price from 20 to 100
        Random random = new Random();
        float randomNumber = 20 + random.nextFloat() * (100 - 20);
        return new Trousers(randomNumber, "brand1", size , color );
    }

    @Override
    public Shirt createShirt(String size, String color) {
        Random random = new Random();
        float randomNumber = 10 + random.nextFloat() * (30 - 10);
        return new Shirt(randomNumber,"brand1", size, color);
    }

    @Override
    public Sweatshirt createSweatShirt(String size, String color) {
        Random random = new Random();
        float randomNumber = 20 + random.nextFloat() * (50 - 20);
        return  new Sweatshirt(randomNumber, "brand1", size, color);
    }
}
