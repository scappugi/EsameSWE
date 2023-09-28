package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.HomePageDAO;
import DataAccess.PrivateAreaDAO;
import DomainModel.Clothes;

public class RegisteredWebUserController {
    CartDAO cartDAO;
    HomePageDAO homepageDAO;
    PrivateAreaDAO privateareaDAO;

    public RegisteredWebUserController(CartDAO cartDAO, HomePageDAO homePageDAO, PrivateAreaDAO privateAreaDAO) {
        this.cartDAO = cartDAO;
        this.homepageDAO = homePageDAO;
        this.privateareaDAO = privateAreaDAO;

    }

    public boolean removeClothesFromCart(Clothes clothes) {
        return cartDAO.removeToCart(clothes);
    }


    public boolean modifyQuantityClothesFromCart(Clothes clothes, int newquantity) {
        return cartDAO.modifyQuantityCartItem(clothes, newquantity);
    }

    public boolean addClothesToCart(Clothes clothes) {
        return cartDAO.addToCart(clothes);
    }

    public boolean buyCart() {
        return cartDAO.payCartItem();
    }

}
