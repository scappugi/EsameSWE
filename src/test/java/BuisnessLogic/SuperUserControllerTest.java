package BuisnessLogic;

import DataAccess.SuperUserDAO;
import DomainModel.SuperUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SuperUserControllerTest {

    @Test
    void addFactory() {
        SuperUserDAO superdao = new SuperUserDAO("C:/sqlite/ShopOnline.db");
        SuperUser superuser = SuperUser.getInstance(1, "super", "password");
        SuperUserController controller = new SuperUserController(superuser, superdao);
        //try to insert a factory that is already in the db
        if (controller.addFactory("f1"))
            System.out.println("factory added");
        else System.out.println("factory already present");
        //insert a factory that isn't in the db
        if (controller.addFactory("f4"))
            System.out.println("factory added");
        else System.out.println("factory already present");
    }

    @Test
    void removeFactory() {
    }

    @Test
    void addNewClothes() {
    }

    @Test
    void modifyExistingClothes() {
    }

    @Test
    void deleteExistingClothes() {
    }
}