package cop4331;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
/**<h1>DatabaseInterface</h1>
 * Handles the operations between the application and db/files*/
public class DatabaseInterface {
	private static DatabaseInterface instance = null;
	File db_dir, users, products, carts, orders, temp;
	
	/**<h1>Constructor</h1>
	 * Creates the needed files and directory.
	 * If something happens please delete the db/ directory
	 * and restart the application
	 * */
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
	
	/**<h1>getInstance</h1>
	 * Needed for the singleton paten
	 * Returns the instance of DatabaseInterface
	 * */
	public static DatabaseInterface getInstance() {
		if(instance == null){
			instance = new DatabaseInterface();
		}
		
		return instance;
	}
	
	/**<h1>attempLogin</h1>
	 * Takes the credentials and compares them with those stored.
	 * @return If there is a match User is returned else null 
	 * */
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
	
	/**<h1>addUser</h1>
	 * Takes the information and attempts to add a new user
	 * @return User if successful otherwise returns null
	 * */
	public User addUser(String username, String password, int sellerFlag){
		try{

			ArrayList<String[]> users = get(this.users);
			FileWriter out = new FileWriter("db/users.txt",true);
			BufferedWriter bw = new BufferedWriter(out);
			int index = 0;
			for(String[] user : users) {
				if(user[1].equals(username)){
					bw.close();
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
	/**<h1>setOrder</h1>
	 *	Adds the order to orders table
	 * */
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
	
	/**<h1>getCart</h1>
	 *	Retries the cart associated with the passed uid
	 *	@return Cart
	 * */
	public Cart getCart(int uid){
		ArrayList<Product> cartItems = new ArrayList<Product>();
		for(String[] entity : get(this.carts)){
			if(entity[0].equals(String.valueOf(uid))){
				cartItems.add(getProduct(Integer.valueOf(entity[1])));
			}
		}
		return new Cart(uid,cartItems.iterator());
	}
	
	/**<h1>updateCartTabel</h1>
	 *	Updates the associated cat this passed products
	 * */
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
			e.printStackTrace();
		}
	}
	
	/**<h1>addProduct</h1>
	 * Attempts to add the product.
	 * @return Products if successful otherwise returns null
	 * */
	public Product addProduct(int sid, String name, String description, double sellprice, double cost){
		try{
			ArrayList<String[]> products = get(this.products);
			FileWriter out = new FileWriter(this.products, true);
			BufferedWriter bw = new BufferedWriter(out);
			int index = products.size();
			bw.newLine();
			bw.write(String.valueOf(index)+","+String.valueOf(sid)+","+name+","+description+","+String.valueOf(sellprice)+","+String.valueOf(cost));
			
			bw.close();
			out.close();
			
			return new Product(index,sid,name,description,sellprice,cost);
		}
		catch(IOException e){
			return null;
		}
	}
	
	public void updateProduct(Product prod){
		try{
			FileWriter out = new FileWriter(this.temp);
			BufferedWriter bw = new BufferedWriter(out);
			for(String[] entity : get(this.products)){
				if(!entity[0].equals(String.valueOf(prod.getId()))){
					bw.write(String.valueOf(entity[0])+","+String.valueOf(entity[1])+","+entity[2]+","+entity[3]+","+String.valueOf(entity[4])+","+String.valueOf(entity[5]));
					bw.newLine();
				}
			}
			bw.close();
			out.close();
			this.products.delete();
			
			out = new FileWriter(this.products);
			bw = new BufferedWriter(out);
			
			for(String[] entity : get(this.temp)){
				bw.write(String.valueOf(entity[0])+","+String.valueOf(entity[1])+","+entity[2]+","+entity[3]+","+String.valueOf(entity[4])+","+String.valueOf(entity[5]));
				bw.newLine();
			}
			
			bw.write(String.valueOf(prod.getId())+","+String.valueOf(prod.getSid())+","+prod.getName()+","+prod.getDescription()+","+String.valueOf(prod.getSellPrice())+","+String.valueOf(prod.getCost()));
			
			bw.close();
			out.close();
		}
		catch(IOException e){
			
		}
	}
	
	public void deleteProduct(Product prod){
		try{
			FileWriter out = new FileWriter(this.temp);
			BufferedWriter bw = new BufferedWriter(out);
			for(String[] entity : get(this.products)){
				if(!entity[0].equals(String.valueOf(prod.getId()))){
					bw.write(String.valueOf(entity[0])+","+String.valueOf(entity[1])+","+entity[2]+","+entity[3]+","+String.valueOf(entity[4])+","+String.valueOf(entity[5]));
					bw.newLine();
				}
			}
			bw.close();
			out.close();
			this.products.delete();
			
			out = new FileWriter(this.products);
			bw = new BufferedWriter(out);
			
			for(String[] entity : get(this.temp)){
				bw.write(String.valueOf(entity[0])+","+String.valueOf(entity[1])+","+entity[2]+","+entity[3]+","+String.valueOf(entity[4])+","+String.valueOf(entity[5]));
				bw.newLine();
			}
			
			bw.close();
			out.close();
		}
		catch(IOException e){
			
		}
	}
	
	/**<h1>getProducts</h1>
	 * Get all products
	 * @return Iterator<Products>
	 * */
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
	
	/**<h1>getProduct</h1>
	 * Gets individual product matching id
	 * @return Product if successful otherwise returns null
	 * */
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
	
	/**<h1>parse</h1>
	 * Parses the file into a string array
	 * @return ArrayList
	 * */
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
	
	/**<h1>get</h1>
	 * Returns the contents of a file
	 * @return ArrayList
	 * */
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
	
}
