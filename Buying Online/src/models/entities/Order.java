package models.entities;

import java.util.ArrayList;

public class Order {
	private int id;
	private User user;
	private ArrayList<OrderProduct> products;
	
	public Order(int id, User user, ArrayList<OrderProduct> products) {
		this.id = id;
		this.user = user;
		this.products = products;
	}

	public int getId() {
		return id;
	}

	public ArrayList<OrderProduct> getProducts() {
		return products;
	}
	
}