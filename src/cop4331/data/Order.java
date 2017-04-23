package cop4331.data;

import java.util.ArrayList;

public class Order {
	private int sid;
	private double total, cost, revenue;
	private ArrayList<Product> products;
	
	public Order(int sid, ArrayList<Product> products){
		this.products = new ArrayList<Product>();
		this.setSid(sid);
		for(Product prod : products){
			add(prod);
		}
	}
	
	public void add(Product prod){
		this.products.add(prod);
		calculate();
	}
	
	public void calculate(){
		total = 0;
		cost = 0;
		for(Product prod : products){
			total += prod.getSellPrice();
			cost += prod.getCost();
		}
		setRevenue(total - cost);
	}
	
	public int getNumProd(){
		return products.size();
	}
	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}
	
	public double getTotal(){
		return total;
	}
	
	public double getCost(){
		return cost;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double d) {
		this.revenue = d;
	}
}
