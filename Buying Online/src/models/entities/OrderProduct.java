package models.entities;

public class OrderProduct {

	private Product product;
	private int id;
	private int quantity;

	public OrderProduct(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getId() {
		return id;
	}

	public double getTotalPriceOrder() {
		return product.getPrice() * quantity;
	}
}