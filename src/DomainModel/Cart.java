package DomainModel;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Map<Clothes, Integer> collection; //clothes and their quantity

    public Cart(){
        collection = new HashMap<>();
    }

    public Map getMap(){
        return  collection;
    }
}
