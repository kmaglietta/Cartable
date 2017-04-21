package cop4331;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DatabaseInterface {
	private static DatabaseInterface instance = null;
	
	protected DatabaseInterface() {
		try {
			File db_dir = new File("db/");
			File users = new File("db/users.txt");
			File products = new File("db/products.txt");
			File carts = new File ("db/carts.txt");
			File orders = new File ("db/oreders.txt");
			
			if (db_dir.mkdirs()) {
				if (users.createNewFile() && products.createNewFile() && carts.createNewFile() && orders.createNewFile()) {
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
		ArrayList<String[]> users = get("users");
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

			ArrayList<String[]> users = get("users");
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
	
	public Cart getUserCart(int uid){
		ArrayList<Product> cartItems = new ArrayList<Product>();
		int id = 0;
		for(String[] entity : get("carts")){
			if(entity[1].equals(String.valueOf(uid))){
				id = Integer.valueOf(entity[0]);
				cartItems.add(getProduct(Integer.valueOf(entity[2])));
			}
		}
		return new Cart(id,uid,cartItems.iterator());
	}
	
	public Iterator<Product> getProducts(){
		ArrayList<Product> products = new ArrayList<Product>();
		for(String[] entity : get("products")){
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
	
	private ArrayList<String[]> get(String file) {
		try {
			FileReader in = new FileReader("db/"+file+".txt");
			BufferedReader br = new BufferedReader(in);
			return parse(br);
		}
		catch (IOException e) {
			System.out.println("No such table exisits");
			return null;
		}
	}
	
}
