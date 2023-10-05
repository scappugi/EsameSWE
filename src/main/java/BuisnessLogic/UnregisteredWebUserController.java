package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.HomePageDAO;
import DomainModel.RegisteredWebUser;

import java.sql.SQLException;

public class UnregisteredWebUserController {

    CartDAO cartDAO;
    HomePageDAO homepageDAO;
    public UnregisteredWebUserController(CartDAO cartDAO, HomePageDAO homepageDAO) {
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
