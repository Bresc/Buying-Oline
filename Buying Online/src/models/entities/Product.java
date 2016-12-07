package models.entities;

public class Product {

	private int id;
	private String name;
	public double price;
	public String srcImg;
	public static int ID_BASE;

	public Product(int id, String name, double price, String srcImg) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.srcImg = srcImg;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSrcImg() {
		return srcImg;
	}

	public void setSrcImg(String srcImg) {
		this.srcImg = srcImg;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", srcImg=" + srcImg + "].";
	}

	public Object[] toObjectVector() {
		return new Object[] { id, name, price };
	}
}