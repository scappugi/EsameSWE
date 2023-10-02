package DataAccess;

import DomainModel.Cart;
import DomainModel.Clothes;

public class CartDAO {

    private HomePageDAO homepageDAO;
    public CartDAO(HomePageDAO homepageDAO){
        this.homepageDAO = homepageDAO;
    }

    public boolean payCartItem(Cart cart){
        boolean flag = false;

        return flag;
    }


}


