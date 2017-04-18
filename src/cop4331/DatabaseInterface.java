package cop4331;
import java.io.*;

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
}
