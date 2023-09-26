package DomainModel;

public class RegisteredWebUser extends WebUser{

    private PrivateArea privateaerea;
    private Cart cart;
    public RegisteredWebUser(int code, String username, String password) {
        super(code, username, password);
        privateaerea = new PrivateArea();
        cart = new Cart();
    }

    public PrivateArea getPrivateArea(){ //instance to access the private area in order to use it in the business logic
        return privateaerea;
    }
}
