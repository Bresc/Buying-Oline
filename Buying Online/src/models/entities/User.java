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
		ID_BASE++;
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

	public Object[] toObjectVector() {
		return new Object[] { name, address, password, sourceImg };
	}
}