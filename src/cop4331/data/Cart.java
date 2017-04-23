package cop4331.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Iterator;

/**<h1>Cart</h1>
 * Holds data related to the Cart<br>
 * users id, collection of products, total price
 * @author */
public class Cart {
	private int uid;
	private ArrayList<Product> products = new ArrayList<Product>();
	private double total;
	
	/**<h1>Constructor</h1>
	 * Initializes the cart to the uid and uses an Iterator to go through the collection of Product
	 * @author */
	public Cart(int uid, Iterator<Product> it){
		setUid(uid);
		setProducts(it);
		setTotal();
	}
	
	/**<h1>add</h1>
	 * Adds a Product to the Carts collection<br>
	 * The total is updated to reflect the addition
	 * @author */
	public void add(Product prod){
		products.add(prod);
		setTotal();
	}
	
	/**<h1>remove</h1>
	 * Removes the passed product from the collection<br>
	 * @return Boolean weather the removal was successful of not
	 * @author */
	public boolean remove(Product prod){
		return products.remove(prod);
	}
	
	/**<h1>clear</h1>
	 * Clears the Cart's collection of products
	 * @author */
	public void clear(){
		products.clear();
	}
	
	/**<h1>getProducts</h1>
	 * @return Iterator from the collection of Products
	 * @author */
	public Iterator<Product> getProducts(){
		return products.iterator();
	}

	/**<h1>setProducts</h1>
	 * Uses a Product Iterator to add the Products to the Cart's collection
	 * @author */
	private void setProducts(Iterator<Product> it) {
		while(it.hasNext()){
			products.add(it.next());
		}
	}
	
	/**<h1>getUid</h1>
	 * @return The user's id associated with the cart
	 * @author */
	public int getUid() {
		return uid;
	}
	
	/**<h1>setUid</h1>
	 * Sets the id of the User associated with the cart
	 * @author */
	private void setUid(int uid) {
		this.uid = uid;
	}
	
	/**<h1>getTotal</h1>
	 * Updates the total then returns that value
	 * @return The total sellPrice of all products
	 * @author */
	public double getTotal() {
		setTotal();
		BigDecimal bd = new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
		return bd.doubleValue();
	}

	/**<h1>setTotal</h1>
	 * Iterates through the Product collection adding up all the sellPrices
	 * @author */
	private void setTotal() {
		Iterator<Product> it = products.iterator();
		total = 0;
		while (it.hasNext()){
			total += it.next().getSellPrice();
		}
	}
}
