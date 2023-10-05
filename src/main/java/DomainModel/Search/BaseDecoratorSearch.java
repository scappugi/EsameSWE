package DomainModel.Search;

import DomainModel.Clothes;

import java.util.ArrayList;

public abstract class BaseDecoratorSearch implements Search{
    protected Search search;

    public BaseDecoratorSearch(Search s){
        search = s;
    }

    public abstract ArrayList<Clothes> searchClothes();
}
