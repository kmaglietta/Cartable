package cop4331;

public class User {
	private int id, sellerFlag;
	private String username;
	
	public User(int id, String username, int flag) {
		setId(id);
		setUsername(username);
		setSellerFlag(flag);
	}
	
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
