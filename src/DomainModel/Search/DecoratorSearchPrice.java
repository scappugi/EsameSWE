package DomainModel.Search;

import DomainModel.Clothes;

import java.util.ArrayList;

public class DecoratorSearchPrice extends BaseDecoratorSearch {
    private int maxprice;

    public DecoratorSearchPrice(Search search, int mp) {
        super(search);
        maxprice = mp;
    }

    @Override
    public ArrayList<Clothes> searchClothes() {
        ArrayList<Clothes> basequery = null;
        try {
            basequery = search.searchClothes();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Clothes> filtered = new ArrayList<>();

        for (Clothes cl : basequery) {
            if (cl.getPrice() <= maxprice)
                filtered.add(cl);
        }
        return filtered;
    }
}
