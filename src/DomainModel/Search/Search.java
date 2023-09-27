package DomainModel.Search;

import DomainModel.Clothes;

import java.util.ArrayList;

public interface Search {
    ArrayList<Clothes> searchClothes() throws ClassNotFoundException;
}
