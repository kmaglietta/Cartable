package cop4331;

import java.util.ArrayList;
import java.util.Iterator;

public class Cart {
	private int id;
	private int uid;
	private ArrayList<Product> products = new ArrayList<Product>();
	private int total;
	
	public Cart(int id,int uid, Iterator<Product> it){
		setId(id);
		setUid(uid);
		setProducts(it);
		setTotal();
	}
	
	public void add(Product prod){
		products.add(prod);
		setTotal();
	}
	
	public Iterator<Product> getProducts(){
		return products.iterator();
	}

	private void setProducts(Iterator<Product> it) {
		while(it.hasNext()){
			products.add(it.next());
		}
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	private void setUid(int uid) {
		this.uid = uid;
	}

	public int getTotal() {
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
