package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.HomePageDAO;
import DataAccess.PrivateAreaDAO;
import DomainModel.Clothes;

import java.util.ArrayList;

public class WebUserController {
    CartDAO cartDAO;
    HomePageDAO homepageDAO;
    PrivateAreaDAO privateareaDAO;

    WebUserController(CartDAO cartDAO, HomePageDAO homePageDAO, PrivateAreaDAO privateAreaDAO){
        this.cartDAO=cartDAO;
        this.homepageDAO=homePageDAO;
        this.privateareaDAO=privateAreaDAO;

    }
    public boolean removeClothesFromCart(Clothes clothes){
       boolean removed = cartDAO.removeToCart(clothes);
        return removed;
    }


    public boolean modifyQuantityClothesFromCart(Clothes clothes,int newquantity){
        return cartDAO.modifyQuantityCartItem(clothes,newquantity);
    }

    public boolean addClothesToCart(Clothes clothes){
       return cartDAO.addToCart(clothes);
    }

    public boolean buyCart(){
        return cartDAO.payCartItem();
    }

}
