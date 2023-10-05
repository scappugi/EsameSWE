package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.HomePageDAO;
import DataAccess.PrivateAreaDAO;
import DomainModel.RegisteredWebUser;
import DomainModel.WebUser;

import java.sql.SQLException;

public class UnregistredWebUserController {

    CartDAO cartDAO;
    HomePageDAO homepageDAO;
    public UnregistredWebUserController(CartDAO cartDAO, HomePageDAO homepageDAO) {
        this.cartDAO = cartDAO;
        this.homepageDAO = homepageDAO;

    }

    public RegisteredWebUser registerWebUser(String username, String password ) throws SQLException {
        if(homepageDAO.registerUser(username,password)) {
            return new RegisteredWebUser(username,password);
        }
        return null;
    }
}
