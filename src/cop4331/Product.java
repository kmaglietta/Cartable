package cop4331;

public class Product {
	private int id, sid;
	private String name, description;
	private double sellPrice, cost;
	
	public Product(int id, int sid, String name, String description, double sellPrice, double cost) {
		this.setId(id);
		this.setSid(sid);
		this.setName(name);
		this.setDescription(description);
		this.setSellPrice(sellPrice);
		this.setCost(cost);
		
		//stroe product in db
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
}
