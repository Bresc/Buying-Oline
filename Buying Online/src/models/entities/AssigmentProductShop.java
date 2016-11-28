package models.entities;

public class AssigmentProductShop {

	private Product product;
	private Shop shop;
	
	public AssigmentProductShop(Product product, Shop shop) {
		this.product = product;
		this.shop = shop;
	}

	public Product getProduct() {
		return product;
	}

	public Shop getShop() {
		return shop;
	}	
	
}
