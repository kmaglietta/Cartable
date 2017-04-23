package cop4331.data;

import java.util.ArrayList;

/**<h1>Order</h1>
 * Manages all the data associated to an order
 * @author */
public class Order {
	private int sid;
	private double total, cost, revenue;
	private ArrayList<Product> products;
	
	/**<h1>Constructor</h1>
	 * Sets the sellerId and the list of products ordered from them
	 * @author */
	public Order(int sid, ArrayList<Product> products){
		this.products = new ArrayList<Product>();
		this.setSid(sid);
		for(Product prod : products){
			add(prod);
		}
	}
	
	/**<h1>add</h1>
	 * Adds the passed product to the collection
	 * @author */
	public void add(Product prod){
		this.products.add(prod);
		calculate();
	}
	
	/**<h1>calculate</h1>
	 * Updates the total and cost then determines the total revenue
	 * @author */
	public void calculate(){
		total = 0;
		cost = 0;
		for(Product prod : products){
			total += prod.getSellPrice();
			cost += prod.getCost();
		}
		setRevenue(total - cost);
	}
	
	/**<h1>getNumProd</h1>
	 * @return the size of the collection of products
	 * @author */
	public int getNumProd(){
		return products.size();
	}
	
	/**<h1>getSid</h1>
	 * @return the Seller id of the order
	 * @author */
	public int getSid() {
		return sid;
	}

	/**<h1>setSid</h1>
	 * Sets the seller id
	 * @author */
	public void setSid(int sid) {
		this.sid = sid;
	}
	
	/**<h1>getTotal</h1>
	 * @return the total sellPrice of products
	 * @author */
	public double getTotal(){
		return total;
	}
	
	/**<h1>getCost</h1>
	 * @return the total cost of products
	 * @author */
	public double getCost(){
		return cost;
	}

	/**<h1>getRevenue</h1>
	 * @return the revenue from all products ordered
	 * @author */
	public double getRevenue() {
		return revenue;
	}

	/**<h1>setRevenue</h1>
	 *	Used to set the value of the revenue
	 * @author */
	private void setRevenue(double d) {
		this.revenue = d;
	}
}
