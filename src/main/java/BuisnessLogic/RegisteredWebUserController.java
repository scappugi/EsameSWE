package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.HomePageDAO;
import DataAccess.PrivateAreaDAO;
import DomainModel.Clothes;
import DomainModel.RegisteredWebUser;
import DomainModel.Search.DecoratorSearchBrand;
import DomainModel.Search.DecoratorSearchPrice;
import DomainModel.Search.DecoratorSearchSize;
import DomainModel.Search.SearchConcrete;

import java.util.ArrayList;

public class RegisteredWebUserController {
    private CartDAO cartDAO;
    private HomePageDAO homepageDAO;
    private PrivateAreaDAO privateareaDAO;
    private RegisteredWebUser registeredwebuser;
    private SearchConcrete search;

    public RegisteredWebUserController(CartDAO cartDAO, HomePageDAO homePageDAO, PrivateAreaDAO privateAreaDAO, RegisteredWebUser registeredwebuser) {
        this.cartDAO = cartDAO;
        this.homepageDAO = homePageDAO;
        this.privateareaDAO = privateAreaDAO;
        this.registeredwebuser = registeredwebuser;
        this.search = new SearchConcrete("C:/sqlite/ShopOnline.db");

    }

    public void accessPrivateArea() {
        if (registeredwebuser.getLogged())
            privateareaDAO.populatePrivateArea(registeredwebuser.getPrivateArea(), registeredwebuser.getUsername()); //dao prende ordini e li inserisce
    }

    public boolean addClothesToCart(Clothes clothes, int qty) {
        boolean found = registeredwebuser.getCart().getMap().containsKey(clothes);
        int qtyclothes = homepageDAO.checkAvailability(clothes);
        boolean available = false;
        if (!found)
            if (qtyclothes - qty >= 0) {
                registeredwebuser.getCart().getMap().put(clothes, qty);
                homepageDAO.updateAvailability(clothes.getCodclothes(), qtyclothes - qty);
                available = true;
            }
        return available;
    }

    public boolean removeClothesFromCart(Clothes clothes) {
        boolean found = registeredwebuser.getCart().getMap().containsKey(clothes);
        int qtyclothes = homepageDAO.checkAvailability(clothes);
        if (found) {
            int newqty = (int) registeredwebuser.getCart().getMap().get(clothes);
            registeredwebuser.getCart().getMap().remove(clothes);
            homepageDAO.updateAvailability(clothes.getCodclothes(), qtyclothes + newqty);
        }
        return found;
    }


    public boolean modifyQuantityClothesFromCart(Clothes clothes, int newqty) {
        boolean found = registeredwebuser.getCart().getMap().containsKey(clothes);
        int qty = (int) registeredwebuser.getCart().getMap().get(clothes);
        int qtyclothes = homepageDAO.checkAvailability(clothes);
        int difference = 0;
        if (!found) {
            if (newqty < qty) {
                difference = qty - newqty;
                homepageDAO.updateAvailability(clothes.getCodclothes(), qtyclothes + difference);
                registeredwebuser.getCart().getMap().put(clothes, newqty);
            } else { //newqty > qty
                difference = newqty - qty;
                if (homepageDAO.checkAvailability(clothes) - difference >= 0) {
                    registeredwebuser.getCart().getMap().put(clothes, newqty);
                    homepageDAO.updateAvailability(clothes.getCodclothes(), qtyclothes - difference);
                }
            }
        }
        return found;
    }

    public boolean buyCart() {
        boolean payed = false;
        if (!registeredwebuser.getCart().getMap().isEmpty()) {
                cartDAO.payCartItem(registeredwebuser.getCart(), registeredwebuser.getUsername());
                payed = true;
                registeredwebuser.getCart().getMap().clear();
            }
        return payed;
    }


    public ArrayList<Clothes> searchByPrice(int price){
       DecoratorSearchPrice decoratorprice = new DecoratorSearchPrice(this.search, price);
        return decoratorprice.searchClothes();
    }

    public ArrayList<Clothes> searchBySize(String size){
        DecoratorSearchSize decoratorsize = new DecoratorSearchSize(this.search, size);
        return decoratorsize.searchClothes();
    }

    public ArrayList<Clothes> searchByBrand(String brand){
        DecoratorSearchBrand decoratorbrand = new DecoratorSearchBrand(this.search, brand);
        return decoratorbrand.searchClothes();
    }

    //mancano i metodi per aggiungere e rimuovere le carte di credito + metodo per login e logout
}
