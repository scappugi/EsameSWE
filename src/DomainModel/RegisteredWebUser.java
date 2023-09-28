package DomainModel;

public class RegisteredWebUser extends WebUser{

    private PrivateArea privateaerea;
    private Cart cart;
    public RegisteredWebUser(int code, String username, String password) {
        super(username, password);
        privateaerea = new PrivateArea();
        cart = new Cart();
    }

    public PrivateArea getPrivateArea(){ //instance to access to the private area in order to use it in the business logic
        return privateaerea;
    }

    public String getUsername(){
        return username;
    }

    public Cart getCart(){
        return cart;
    }
}
