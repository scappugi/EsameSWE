package DomainModel;

import java.util.ArrayList;

public abstract class WebUser {
    protected boolean logged;
    protected String username;
    protected String password;

    private ArrayList<DebitCard> debitcards;


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

    public void newCard(DebitCard newcard) {
        debitcards.add(newcard);
    }

    public DebitCard getCard(DebitCard ocard) {

        for (DebitCard cards : debitcards) {
            if (cards.getCodCard() == ocard.getCodCard()) {
                //find card
                return cards;
            }
        }
        return null; //failed
    }
}


