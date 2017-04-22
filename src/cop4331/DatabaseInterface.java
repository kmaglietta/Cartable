package cop4331;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DatabaseInterface {
	private static DatabaseInterface instance = null;
	File db_dir, users, products, carts, orders, temp;
	protected DatabaseInterface() {
		try {
			db_dir = new File("db/");
			users = new File("db/users.txt");
			products = new File("db/products.txt");
			carts = new File ("db/carts.txt");
			orders = new File ("db/oreders.txt");
			temp = new File ("db/temp.txt");
			
			if (db_dir.mkdirs()) {
				if (users.createNewFile() && products.createNewFile() && carts.createNewFile() && orders.createNewFile() && temp.createNewFile()) {
					System.out.println("Files created");
				}
				else {
					System.out.println("One or more file exists");
				}
			}
			else {
				System.out.println("Directory already exists");
			}
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}
		finally {
			
		}
	}
	
	public static DatabaseInterface getInstance() {
		if(instance == null){
			instance = new DatabaseInterface();
		}
		
		return instance;
	}
	
	public User attemptLogin(String username, String password){
		ArrayList<String[]> users = get(this.users);
		for(String[] user : users) {
			if(user[1].equals(username)){
				if(user[2].equals(password)){
					return new User(Integer.valueOf(user[0]),user[1],Integer.valueOf(user[3]));
				}
			}
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	public User addUser(String username, String password, int sellerFlag){
		try{

			ArrayList<String[]> users = get(this.users);
			FileWriter out = new FileWriter("db/users.txt",true);
			BufferedWriter bw = new BufferedWriter(out);
			int index = 0;
			for(String[] user : users) {
				if(user[1].equals(username)){
					throw new NullPointerException();
				}
				index++;
			}
			bw.newLine();
			bw.write(index+","+username+","+password+","+sellerFlag);
			
			bw.close();
			out.close();
			
			return new User(index, username, sellerFlag);
		}
		catch(NullPointerException e){
			return null;
		}
		catch(IOException e){
			return null;
		}
		
	}
	
	public Cart getCart(int uid){
		ArrayList<Product> cartItems = new ArrayList<Product>();
		for(String[] entity : get(this.carts)){
			if(entity[0].equals(String.valueOf(uid))){
				cartItems.add(getProduct(Integer.valueOf(entity[1])));
			}
		}
		return new Cart(uid,cartItems.iterator());
	}
	
	public void updateCartTabel(int uid, Cart cart){
		try {
			FileWriter out = new FileWriter(temp);
			BufferedWriter bw = new BufferedWriter(out);
			for(String[] entity : get(this.carts)){
				if(!entity[0].equals(String.valueOf(uid))){
					bw.write(entity[0]+","+entity[1]);
					bw.newLine();
				}
			}
			bw.close();
			out.close();
			this.carts.delete();
			
			out = new FileWriter(this.carts);
			bw = new BufferedWriter(out);
			
			for(String[] entity : get(this.temp)){
				bw.write(entity[0]+","+entity[1]);
				bw.newLine();
			}
			
			Iterator<Product> it = cart.getProducts();
			Product prod = null;
			while(it.hasNext()){
				prod = it.next();
				bw.write(String.valueOf(uid) + "," + String.valueOf(prod.getId()));
				bw.newLine();
			}
			bw.close();
			out.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Iterator<Product> getProducts(){
		ArrayList<Product> products = new ArrayList<Product>();
		for(String[] entity : get(this.products)){
			products.add(new Product(
					Integer.valueOf(entity[0]),
					Integer.valueOf(entity[1]),
					entity[2],
					entity[3],
					Double.valueOf(entity[4]),
					Double.valueOf(entity[5])
					));
		}
		return products.iterator();
	}
	
	public Product getProduct(int id){
		Iterator<Product> prods = getProducts();
		Product prod = null;
		while(prods.hasNext()){
			prod = prods.next();
			if(prod.getId() == id){
				return prod; 
			}
		}
		return prod;
	}
	
	private ArrayList<String[]> parse(BufferedReader br){
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String[]> entities = new ArrayList<String[]>();
		try{
			while(br.ready()){
				data.add(br.readLine());
			}
			
			for(String s : data) {
				entities.add(s.split(","));
			}

			br.close();
		}
		catch(IOException e){}
		
		return entities;
	}
	
	private ArrayList<String[]> get(File file) {
		try {
			FileReader in = new FileReader(file);
			BufferedReader br = new BufferedReader(in);
			return parse(br);
		}
		catch (IOException e) {
			System.out.println("No such table exisits");
			return null;
		}
	}

	public void setOrder(int id, Iterator<Product> products) {
		try{
			FileWriter out = new FileWriter(orders);
			BufferedWriter bw = new BufferedWriter(out);
			Product prod = null;
			
			while(products.hasNext()){
				prod = products.next();
				bw.write(id + "," + prod.getSid() + "," + prod.getId());
				bw.newLine();
			}
			
			
			bw.close();
			out.close();
		}
		catch(IOException e){
			
		}
		
	}
	
}
