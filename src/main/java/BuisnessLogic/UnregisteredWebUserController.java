package BuisnessLogic;

import DataAccess.CartDAO;
import DataAccess.HomePageDAO;
import DomainModel.RegisteredWebUser;

import java.sql.SQLException;

public class UnregisteredWebUserController {

    private HomePageDAO homepageDAO;

    public UnregisteredWebUserController(HomePageDAO homepageDAO) {
        this.homepageDAO = homepageDAO;

    }

    public RegisteredWebUser registerWebUser(String username, String password) throws SQLException {
        if (homepageDAO.registerUser(username, password)) {
            return new RegisteredWebUser(username, password);
        }
        return null;
    }
}
