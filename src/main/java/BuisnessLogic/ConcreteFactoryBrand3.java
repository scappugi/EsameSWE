package BuisnessLogic;

import DomainModel.Shirt;
import DomainModel.Sweatshirt;
import DomainModel.Trousers;

import java.util.Random;

public class ConcreteFactoryBrand3 implements AbstractFactory{
    private String name;
    public String getName() {
        return name;
    }



    public ConcreteFactoryBrand3(String n){
        this.name = n;
    }

    @Override
    public Trousers createTrousers(String size, String color, int code) {
        Random random = new Random();
        float randomNumber = 20 + random.nextFloat() * (50 - 20);
        return  new Trousers(randomNumber, "brand3", size, color, code);
    }

    @Override
    public Shirt createShirt(String size, String color, int code) {
        Random random = new Random();
        float randomNumber = 5 + random.nextFloat() * (20 - 5);
        return  new Shirt(randomNumber,"brand3" ,size, color, code);
    }

    @Override
    public Sweatshirt createSweatShirt(String size, String color, int code) {
        Random random = new Random();
        float randomNumber = 5 + random.nextFloat() * (20 - 5);
        return  new Sweatshirt(randomNumber,"brand3" ,size, color, code);
    }
}
