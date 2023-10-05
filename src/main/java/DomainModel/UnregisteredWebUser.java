package DomainModel;

public class UnregisteredWebUser extends WebUser {
    private Cart cart;

    public UnregisteredWebUser(int code) {
        super(code);
        cart = new Cart();
    }

    public Cart getCart(){
        return cart;
    }
}
