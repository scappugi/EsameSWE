package DataAccess;

import DomainModel.Cart;
import DomainModel.Clothes;

public class CartDAO {
    public CartDAO(){} //costruttore vuoto

    public boolean addToCart(Clothes clothes, Cart cart){

        return true;
    }

    public boolean removeToCart(Clothes clothes){
        return true;
    }

    public boolean modifyQuantityCartItem(Clothes clothes, int newquantity){
        return true;
    }
    public boolean viewAllCartItem(Clothes clothes){
        return true;
    }

    public boolean payCartItem(){
        return true;
    }


}


