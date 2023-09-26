package DomainModel;

public class WebUser {
    private int coduser;
    private boolean logged;
    private String username;
    private String password;


    public WebUser(int c, String u, String p) {
        coduser = c;
        logged = false;
        username = u;
        password = p;
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
