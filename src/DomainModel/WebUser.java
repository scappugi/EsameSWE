package DomainModel;

public class WebUser {
    private boolean logged;
    private String username;
    private String password;


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
