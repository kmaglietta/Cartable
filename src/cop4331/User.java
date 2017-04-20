package cop4331;

public class User {
	private int id;
	private String username;
	/*private String firstName;
	private String lastName;*/
	
	public User(int id, String username) {
		this.id = id;
		this.username = username;
	}
	
	public int getId() {
		return id;
	}
	
	public String username() {
		return username;
	}
}
