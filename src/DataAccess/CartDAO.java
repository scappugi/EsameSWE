package DataAccess;

import BuisnessLogic.Clothes;

public class CartDAO {
    public CartDAO(){} //costruttore vuoto

    public boolean addToCart(Clothes clothes){
        return true;
    }

    public boolean removeToCart(Clothes clothes){
        return true;
    }

    public boolean modifyCartItem(Clothes clothes){
        return true;
    }

    public boolean searchCartItem(Clothes clothes){
        return true;
    }

    public boolean viewAllCartItem(Clothes clothes){
        return true;
    }

    public boolean payCartItem(Clothes clothes){
        return true;
    }
}
