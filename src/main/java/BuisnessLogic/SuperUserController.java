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
        if(addFactory("f1")){
            AbstractFactory factory1 = new ConcreteFactoryBrand1("f1");
            superuser.getFactories().add(factory1);
        }
        if(addFactory("f2")){
            AbstractFactory factory2 = new ConcreteFactoryBrand2("f2");
            superuser.getFactories().add(factory2);
        }
        if(addFactory("f3")){
            AbstractFactory factory3 = new ConcreteFactoryBrand3("f3");
            superuser.getFactories().add(factory3);
        }
    }

    public boolean addFactory(String name){
        if(superUserDAO.addFactory(name))
            return true;
        else return false;
    }

    public boolean removeFactory(String name){
        if(superUserDAO.removeFactory(name))
            return true;
        else return false;
    }

   public boolean addNewClothes(String category, String size, String color, int qnt, String factory, int code) {
        for (AbstractFactory it : superuser.getFactories()) {
            Clothes clothes;
            if (it.getName().equals(factory)) { //check if the factory is right
                if (category.equals("shirt")) { //look what category is clothes
                    clothes = it.createShirt(size, color, code);
                    superUserDAO.addNewClothes(clothes, it.getName(), qnt); //pass the qnt to db
                    return true;
                } else if (category.equals("sweatshirt")) {
                    clothes = it.createSweatShirt(size, color, code);
                    superUserDAO.addNewClothes(clothes, it.getName(), qnt);
                    return true;
                } else if (category.equals("trousers")) {
                    clothes = it.createTrousers(size, color, code);
                    superUserDAO.addNewClothes(clothes, it.getName(), qnt);
                    return true;
                }
            }
        }

        return false;
    }


    public boolean modifyExistingClothes(int code, int qty) {
        if (superUserDAO.updateQtyClothes(code, qty))
            return true;
        else return false;
    }

    public boolean deleteExistingClothes(int code) {
        if (superUserDAO.removeClothes(code))
            return true;
        else return false;
    }
}
