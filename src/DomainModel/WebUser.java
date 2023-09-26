package DomainModel;

public class WebUser {
    private int coduser;
    private boolean logged;
    private String username;
    private String password;


    public WebUser(int c, String u, String p) { //for registered users
        coduser = c;
        logged = false;
        username = u;
        password = p;
    }

    public WebUser(int c) { //for unregistered users
        coduser = c;
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

    public int getCod() {
        return coduser;
    }

}
