package BuisnessLogic;

import DataAccess.SuperUserDAO;
import DomainModel.Clothes;
import DomainModel.SuperUser;

import java.util.Objects;

public class SuperUserController {

    SuperUser superuser;
    SuperUserDAO superUserDAO;

    public SuperUserController(SuperUser su, SuperUserDAO suD) {
        superuser = su;
        superUserDAO = suD;
        AbstractFactory factory1 = new ConcreteFactoryBrand1();
        // dao.aggiugi fabbrica
        AbstractFactory factory2 = new ConcreteFactoryBrand2();
        AbstractFactory factory3 = new ConcreteFactoryBrand3();
        superuser.getFactories().add(factory1);
        superuser.getFactories().add(factory2);
        superuser.getFactories().add(factory3);

    }

    public boolean addNewClothes(String category, String size, String color, int qnt, AbstractFactory factory) {
        for (AbstractFactory it : superuser.getFactories()) {
            Clothes clothes;
            if (superuser.getFactories() == factory) { //check if the factory is right
                if (category.equals("shirt")) { //look what category is clothes
                    clothes = it.createShirt(size, color);
                    superUserDAO.addNewClothes(clothes, qnt); //pass the qnt to db
                    return true;
                } else if (category.equals("sweatshirt")) {
                    clothes = it.createSweatShirt(size, color);
                    superUserDAO.addNewClothes(clothes, qnt);
                    return true;
                } else if (category.equals("trousers")) {
                    clothes = it.createTrousers(size, color);
                    superUserDAO.addNewClothes(clothes, qnt);
                    return true;
                }
            }
        }

        return false;
    }


    public boolean modifyExistingClothes(Clothes oldclothes, Clothes newclothes) {

        return true;
    }

    public boolean deleteExistingClothes(Clothes clothes) {

        return true;
    }
}
