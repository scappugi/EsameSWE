package DomainModel;

import java.util.ArrayList;

public abstract class WebUser {
    protected boolean logged;
    protected String username;
    protected String password;

    protected ArrayList<DebitCard> debitcards;


    public WebUser(String u, String p) { //for registered users
        logged = false;
        username = u;
        password = p;
    }

    public WebUser() { //for unregistered users
        logged = false;
        username = null;
        password = null;
    }

    public boolean getLogged() {
        return logged;
    }

    public void setLogged(boolean l) {
        logged = l;
    }

    public ArrayList<DebitCard> getDebitcards() {
        return debitcards;
    }

}


