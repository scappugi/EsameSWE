package DomainModel;

public class WebUser {
    protected boolean logged;
    protected String username;
    protected String password;


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


}
