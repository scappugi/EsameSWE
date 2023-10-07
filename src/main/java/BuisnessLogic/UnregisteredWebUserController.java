package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.HomePageDAO;
import DomainModel.Clothes;
import DomainModel.RegisteredWebUser;
import DomainModel.Search.DecoratorSearchBrand;
import DomainModel.Search.DecoratorSearchPrice;
import DomainModel.Search.DecoratorSearchSize;
import DomainModel.Search.SearchConcrete;

import java.sql.SQLException;
import java.util.ArrayList;

public class UnregisteredWebUserController {

    private HomePageDAO homepageDAO;
    private SearchConcrete search;

    public UnregisteredWebUserController(HomePageDAO homepageDAO) {
        this.homepageDAO = homepageDAO;
        this.search = new SearchConcrete("C:/sqlite/ShopOnline.db");

    }

    public RegisteredWebUser registerWebUser(String username, String password) throws SQLException {
        if (homepageDAO.registerUser(username, password)) {
            return new RegisteredWebUser(username, password);
        }
        return null;
    }

    public ArrayList<Clothes> searchByPrice(int price) {
        DecoratorSearchPrice decoratorprice = new DecoratorSearchPrice(this.search, price);
        return decoratorprice.searchClothes();
    }

    public ArrayList<Clothes> searchBySize(String size) {
        DecoratorSearchSize decoratorsize = new DecoratorSearchSize(this.search, size);
        return decoratorsize.searchClothes();
    }

    public ArrayList<Clothes> searchByBrand(String brand) {
        DecoratorSearchBrand decoratorbrand = new DecoratorSearchBrand(this.search, brand);
        return decoratorbrand.searchClothes();
    }
}
