package cop4331.singles;

import cop4331.data.Cart;
import cop4331.data.Order;
import cop4331.data.Product;
import cop4331.data.User;

/**<h1>Session</h1>
 * Holds data for pages to easily access and pass between eachother
 * @author */
public class Session {
	private static Session instance = null;
	private User user = null;
	private Product product = null;
	private Cart cart = null;
	private Order order = null;
	
	protected Session() {
		
	}
	
	/**<h1>getInstance</h1>
	 * @return the instance of the Session
	 * @author */
	public static Session getInstance() {
		if (instance == null) {
			instance = new Session();
		}
		
		return instance;
	}
	
	/**<h1>clear</h1>
	 * Clears the session data. Used for sign out
	 * @author */
	public void clear() {
		setUser(null);
		setProduct(null);
		setCart(null);
		setOrder(null);
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
	
	/**<h1>getCart</h1>
	 * Used to retrieve the cart of the user
	 * @author */
	public Cart getCart() {
		if(user != null && cart == null){
			setCart(DatabaseInterface.getInstance().getCart(user.getId()));
		}
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
