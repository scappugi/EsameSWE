package DataAccess;

import DomainModel.Clothes;

public class SuperUserDAO {
    public SuperUserDAO(){}
    public boolean addNewClothes(Clothes clothes,String codstorage){
        return true;
    } //method with codstorage not null

    public boolean addNewClothes(Clothes clothes){

        return true;
    }

    public boolean removeClothes(Clothes clothes){
        return true;
    }

    public boolean updateClothes(Clothes clothes){
        return true;
    }


}
