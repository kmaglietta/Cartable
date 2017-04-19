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
	
	@SuppressWarnings("resource")
	public void getUsers(){
		try {
			FileReader in = new FileReader("db/users.txt");
			BufferedReader br = new BufferedReader(in);
			ArrayList<String> data = new ArrayList<String>();
			ArrayList<String[]> user = new ArrayList<String[]>();
			
			while(br.ready()){
				data.add(br.readLine());
			}
			for(String s : data) {
				user.add(s.split(","));
			}
			for (String[] sa : user) {
				for (String s : sa){
					System.out.println(s);
				}
			}
		}
		catch (IOException e) {
			System.out.println("Error in DatabaseInterface.getUser()");
		}
	}
	
}
