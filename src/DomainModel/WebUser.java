package DomainModel;

public class WebUser {
    private int coduser;
    private boolean logged;


    public WebUser(int c) {
        coduser = c;
        logged = false;
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
