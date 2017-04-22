package cop4331;

import java.util.ArrayList;
import java.util.Iterator;

public class Cart {
	private int uid;
	private ArrayList<Product> products = new ArrayList<Product>();
	private double total;
	
	public Cart(int uid, Iterator<Product> it){
		setUid(uid);
		setProducts(it);
		setTotal();
	}
	
	public void add(Product prod){
		products.add(prod);
		setTotal();
	}
	public boolean remove(Product prod){
		return products.remove(prod);
	}
	
	public Iterator<Product> getProducts(){
		return products.iterator();
	}

	private void setProducts(Iterator<Product> it) {
		while(it.hasNext()){
			products.add(it.next());
		}
	}

	public int getUid() {
		return uid;
	}

	private void setUid(int uid) {
		this.uid = uid;
	}

	public double getTotal() {
		setTotal();
		return total;
	}

	private void setTotal() {
		Iterator<Product> it = products.iterator();
		total = 0;
		while (it.hasNext()){
			total += it.next().getSellPrice();
		}
	}
}
