package BuisnessLogic;

import DataAccess.SuperUserDAO;
import DomainModel.Clothes;
import DomainModel.SuperUser;

public class SuperUserController {

    SuperUser superuser;
    SuperUserDAO superUserDAO;
    public SuperUserController (SuperUser su, SuperUserDAO suD){
        superuser = su;
        superUserDAO=suD;

    }
    public boolean catalogaNuoviVestiti(Clothes clothes, AbstractFactory factory){
        superUserDAO.addNewClothes(clothes);
        return true;
    }


    public boolean modifyExistingClothes(Clothes oldclothes, Clothes newclothes){

        return true;
    }

    public boolean deleateExistingClothes(Clothes clothes){

        return true;
    }

    public boolean buyNewClothes(){
        return true;
    }
    private SuperUser superUser;
}
