package cop4331.singles;

import cop4331.data.Cart;
import cop4331.data.Order;
import cop4331.data.Product;
import cop4331.data.User;

public class Session {
	private static Session instance = null;
	private User user = null;
	private Product product = null;
	private Cart cart = null;
	private Order order = null;
	
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}