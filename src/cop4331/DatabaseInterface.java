package cop4331;
import java.io.*;
import java.util.ArrayList;

public class DatabaseInterface {
	private static DatabaseInterface instance = null;
	
	protected DatabaseInterface() {
		try {
			File db_dir = new File("db/");
			File users = new File("db/users.txt");
			File products = new File("db/products.txt");
			File orders = new File ("db/oreders.txt");
			
			if (db_dir.mkdirs()) {
				if (users.createNewFile() && products.createNewFile() && orders.createNewFile()) {
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
		ArrayList<String[]> users = getUsers();
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

			ArrayList<String[]> users = getUsers();
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

	
	private ArrayList<String[]> getUsers(){
		try {
			FileReader in = new FileReader("db/users.txt");
			BufferedReader br = new BufferedReader(in);
			ArrayList<String> data = new ArrayList<String>();
			ArrayList<String[]> users = new ArrayList<String[]>();
			
			while(br.ready()){
				data.add(br.readLine());
			}
			
			for(String s : data) {
				users.add(s.split(","));
			}
			
			br.close();
			in.close();
			
			return users;
		}
		catch (IOException e) {
			System.out.println("Error in DatabaseInterface.getUser()");
			return null;
		}
	}
	
}
