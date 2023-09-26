package DataAccess;

import BuisnessLogic.Clothes;
import BuisnessLogic.Shirt;
import BuisnessLogic.Sweatshirt;
import BuisnessLogic.Trousers;
import DomainModel.WebUser;

import java.util.ArrayList;

public class HomePageDAO {

    public boolean registerUser(WebUser webUser){
        return true;
    }

    public boolean registerCreditCard(/**/){
        return true;
    }

    public boolean checkAvailability(Clothes clothes){
        return true;
    }

    public boolean checkDescription(Clothes clothes){
        return true;
    }

    public ArrayList<Trousers> getTrousers(/*bisogna mettere la roba su cui filtrare(dal DB)*/){
        ArrayList<Trousers> trousers1 = null;
        return trousers1 ; //dovrà ritornare qualcosa di sensato
    }

    public ArrayList<Sweatshirt> getSweatshirt(){
        ArrayList<Sweatshirt> sweatshirts = null;
        return sweatshirts;
    }

    public ArrayList<Shirt> getShirt(){
        ArrayList<Shirt> shirts = null;
        return shirts;
    }





}
