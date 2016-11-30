package models.entities;

public class User {
	private int id;
	private String name;
	private String address;
	private String password;
	private String sourceImg;
	public static int ID_BASE;

	public User(String name, String address, String password, String sourceImg) {
		this.name = name;
		this.address = address;
		this.password = password;
		this.sourceImg = sourceImg;
		this.id = ID_BASE++;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public String getSourceImg() {
		return sourceImg;
	}

	public static int getID_BASE() {
		return ID_BASE;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSourceImg(String sourceImg) {
		this.sourceImg = sourceImg;
	}

	public static void setID_BASE(int iD_BASE) {
		ID_BASE = iD_BASE;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", address=" + address + "]";
	}

	public Object[] toObjectVector() {
		return new Object[] { id, name, address};
	}
}