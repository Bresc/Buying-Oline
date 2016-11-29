package models.entities;

public class OrderProduct {
		
	private Product product;
	private int id;
	private int quantity;
	public static  int ID_BASE = 0;
	
	public OrderProduct(Product product ,int quantity) {
		this.setId(ID_BASE++);
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
		
}