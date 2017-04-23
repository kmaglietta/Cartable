package cop4331.data;
/**<h1>User</h1>
 * Manages the data related to a user
 * @author */
public class User {
	private int id, sellerFlag;
	private String username;
	
	/**<h1>Constructor</h1>
	 * Sets the id, username, and sellerFlag
	 * @author */
	public User(int id, String username, int flag) {
		setId(id);
		setUsername(username);
		setSellerFlag(flag);
	}
	
	/**<h1>isSeller</h1>
	 * Converts the value of the int isSeller to boolean<br>
	 * 0 -> false, 1 -> true
	 * Still surprised this doesn't exist...
	 * @author */
	public boolean isSeller(){
		if(sellerFlag == 1) return true;
		else return false;
	}
	
	//Auto-generated getter/setter methods
	public int getId() {
		return id;
	}
	
	private void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	private void setUsername(String username){
		this.username = username;
	}

	public int getSellerFlag() {
		return sellerFlag;
	}

	private void setSellerFlag(int sellerFlag) {
		this.sellerFlag = sellerFlag;
	}
}
