package DomainModel;

public class UnregisteredWebUser extends WebUser {
    private Cart cart;

    public UnregisteredWebUser() {
        super();
        cart = new Cart();
    }

    public Cart getCart(){
        return cart;
    }
}
