package DomainModel;

public class SuperUser extends WebUser {

    private static SuperUser istance = null;

    private SuperUser(int c, String u, String p) {
        super(c, u, p);
    }

    public static SuperUser getIstance(int c, String u, String p) {
        if (istance == null)
            istance = new SuperUser(c, u, p);
        return istance;
    }
}
