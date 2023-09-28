package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.HomePageDAO;
import DataAccess.PrivateAreaDAO;
import DomainModel.Clothes;
import DomainModel.RegisteredWebUser;

public class RegisteredWebUserController {
    CartDAO cartDAO;
    HomePageDAO homepageDAO;
    PrivateAreaDAO privateareaDAO;
    RegisteredWebUser registeredwebuser;

    public RegisteredWebUserController(CartDAO cartDAO, HomePageDAO homePageDAO, PrivateAreaDAO privateAreaDAO, RegisteredWebUser registeredwebuser) {
        this.cartDAO = cartDAO;
        this.homepageDAO = homePageDAO;
        this.privateareaDAO = privateAreaDAO;
        this.registeredwebuser = registeredwebuser;

    }

    public void login(String username, String password) {
        if (homepageDAO.login(username, password)) {
            registeredwebuser.setLogged(true);
        }
    }

    public void logout() {
        registeredwebuser.setLogged(false);
    }

    public void accessPrivateArea() {
        if (registeredwebuser.getLogged())
            privateareaDAO.populatePrivateArea(registeredwebuser.getPrivateArea(),registeredwebuser.getUsername()); //dao prende ordini e li inserisce
    }

    public boolean addClothesToCart(Clothes clothes, int qty) {
        boolean found = registeredwebuser.getCart().getMap().containsKey(clothes);
        if(!found)
            registeredwebuser.getCart().getMap().put(clothes,qty);
        return found;
    }

    public boolean removeClothesFromCart(Clothes clothes) {
        boolean found = registeredwebuser.getCart().getMap().containsKey(clothes);
        if(found)
            registeredwebuser.getCart().getMap().remove(clothes);
        return found;
    }


    public boolean modifyQuantityClothesFromCart(Clothes clothes, int newqty) {
        boolean found = registeredwebuser.getCart().getMap().containsKey(clothes);
        if(found)
            registeredwebuser.getCart().getMap().put(clothes,newqty);
        return found;
    }

    public boolean buyCart() {
        return cartDAO.payCartItem();
    }

}