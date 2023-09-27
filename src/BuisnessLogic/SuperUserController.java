package BuisnessLogic;

import DomainModel.Clothes;
import DomainModel.SuperUser;

public class SuperUserController {
    public SuperUserController (SuperUser superUser){

    }
    public boolean catalogaNuoviVestiti(Clothes clothes){

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
