package DomainModel.Search;

import DomainModel.Clothes;

import java.util.ArrayList;

public class DecoratorSearchBrand extends BaseDecoratorSearch{
    private String brand;

    public DecoratorSearchBrand(Search search, String brand) {
        super(search);
        this.brand = brand;
    }


    @Override
    public ArrayList<Clothes> searchClothes() {
        ArrayList<Clothes> basequery = null;
        ArrayList<Clothes> filtered = new ArrayList<>();
        try {
            basequery = search.searchClothes();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(Clothes cl : basequery){
            if(cl.getBrand().equals(brand))
                filtered.add(cl);
        }
        return filtered;
    }
}
