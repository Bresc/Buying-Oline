package models.entities;

public class AssignmentProductShop {

	private int id;
	private Product product;
	private Shop shop;
	public static int ID_BASE = 0;

	public AssignmentProductShop(Product product, Shop shop) {
		this.id = ID_BASE++;;
		this.product = product;
		this.shop = shop;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shop getShop() {
		return shop;
	}
	
	@Override
	public String toString() {
		return "AssignmentProductShop [id=" + id + ", product=" + product + ", shop=" + shop + "]";
	}

	public void setshop(Shop shop) {
		this.shop = shop;
	}
}
