package DomainModel;

public class SuperUser extends WebUser {

    private static SuperUser istance = null;

    private SuperUser(int code, String username, String password) {
        super(code, username, password);
    }

    public static SuperUser getIstance(int code, String username, String password) {
        if (istance == null)
            istance = new SuperUser(code, username, password);
        return istance;
    }
}
