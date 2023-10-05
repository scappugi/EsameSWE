package DomainModel;

import BuisnessLogic.AbstractFactory;
import BuisnessLogic.ConcreteFactoryBrand1;
import BuisnessLogic.ConcreteFactoryBrand2;
import BuisnessLogic.ConcreteFactoryBrand3;

import java.util.ArrayList;

public class SuperUser extends WebUser {

    private static SuperUser istance = null;

    public ArrayList<AbstractFactory> getFactories() {
        return factories;
    }

    private ArrayList<AbstractFactory> factories = new ArrayList<>();

    private SuperUser(String username, String password) {
        super(username, password);
        //populate the arraylist with all factory

    }

    public static SuperUser getIstance(int code, String username, String password) {
        if (istance == null)
            istance = new SuperUser(username, password);
        return istance;
    }
}
