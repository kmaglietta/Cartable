package cop4331;

public class Session {
	private static Session instance = null;
	private User user = null;
	private Product product = null;
	private Cart cart = null;
	
	protected Session() {
		
	}
	
	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}
		
		return instance;
	}
	
	public void clear() {
		setUser(null);
		setProduct(null);
		setCart(null);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int getUid(){
		return this.user.getId();
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		if(user != null && cart == null){
			setCart(DatabaseInterface.getInstance().getCart(user.getId()));
		}
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
}
