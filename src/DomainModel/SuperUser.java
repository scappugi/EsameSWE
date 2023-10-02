package DomainModel;

import BuisnessLogic.AbstractFactory;

import java.util.ArrayList;

public class SuperUser extends WebUser {

    private static SuperUser istance = null;
    ArrayList<AbstractFactory> factories;

    private SuperUser(String username, String password) {
        super(username, password);
    }

    public static SuperUser getIstance(int code, String username, String password) {
        if (istance == null)
            istance = new SuperUser(username, password);
        return istance;
    }
}
