package DataAccess;

import DomainModel.Cart;
import DomainModel.Clothes;

public class CartDAO {

    private HomePageDAO homepageDAO;
    public CartDAO(HomePageDAO homepageDAO){
        this.homepageDAO = homepageDAO;
    }

    public void payCartItem(Cart cart){
        //crea ordine
    }


}


