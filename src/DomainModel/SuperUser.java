package DomainModel;

import BuisnessLogic.AbstractFactory;
import BuisnessLogic.ConcreteFactoryBrand1;
import BuisnessLogic.ConcreteFactoryBrand2;
import BuisnessLogic.ConcreteFactoryBrand3;

import java.util.ArrayList;

public class SuperUser extends WebUser {

    private static SuperUser istance = null;
    ArrayList<AbstractFactory> factories = new ArrayList<>();

    private SuperUser(String username, String password) {
        super(username, password);
        //populate the arraylist with all factory
        AbstractFactory factory1 = new ConcreteFactoryBrand1();
        AbstractFactory factory2 = new ConcreteFactoryBrand2();
        AbstractFactory factory3 = new ConcreteFactoryBrand3();
        factories.add(factory1);
        factories.add(factory2);
        factories.add(factory3);
    }

    public static SuperUser getIstance(int code, String username, String password) {
        if (istance == null)
            istance = new SuperUser(username, password);
        return istance;
    }
}
