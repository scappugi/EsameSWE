package DomainModel.Search;

import DomainModel.Clothes;

import java.util.ArrayList;

public class DecoratorSearchSize extends BaseDecoratorSearch {
    private String size;

    public DecoratorSearchSize(Search search, String size) {
        super(search);
        this.size = size;
    }


    @Override
    public ArrayList<Clothes> searchClothes(String databaseURL) {
        ArrayList<Clothes> basequery = null;
        ArrayList<Clothes> filtered = new ArrayList<>();
        try {
            basequery = search.searchClothes(databaseURL);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(Clothes cl : basequery){
            if(cl.getSize().equals(size))
                filtered.add(cl);
        }
        return filtered;
    }
}
