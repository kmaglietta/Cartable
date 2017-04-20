package cop4331;

public class Session {
	private static Session instance = null;
	private User user = null;
	private Product product = null;
	
	protected Session() {
		
	}
	
	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}
		
		return instance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
