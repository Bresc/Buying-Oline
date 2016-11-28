package models.entities;

public class AssignmentProductShop {

	private int id;
	private Product product;
	private Shop shop;
	
	public AssignmentProductShop(int id, Product product, Shop shop) {
		this.id = id;
		this.product = product;
		this.shop = shop;
	}

	public int  getId() {
		return id;
	}
	
	public void setId(int id){
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
	
	public void setshop(Shop shop) {
		this.shop = shop;
	}
}
