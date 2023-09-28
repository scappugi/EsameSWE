package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.HomePageDAO;
import DataAccess.PrivateAreaDAO;
import DomainModel.RegisteredWebUser;
import DomainModel.WebUser;

public class UnregistredWebUserController {

    CartDAO cartDAO;
    HomePageDAO homepageDAO;
    public UnregistredWebUserController(CartDAO cartDAO, HomePageDAO homepageDAO) {
        this.cartDAO = cartDAO;
        this.homepageDAO = homepageDAO;

    }

    public RegisteredWebUser registerWebUser(String username, String password ){
        if(homepageDAO.registerUser(username,password))
        return
    }
}
