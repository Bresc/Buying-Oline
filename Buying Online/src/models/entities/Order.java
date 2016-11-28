package models.entities;

import java.util.ArrayList;

public class Order {
	private int id;
	private User user;
	private State state;
	private ArrayList<OrderProduct> products;
	
	public Order(int id, User user, ArrayList<OrderProduct> products, State state) {
		this.id = id;
		this.user = user;
		this.products = products;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public ArrayList<OrderProduct> getProducts() {
		return products;
	}
	
	public State getState() {
		return state;
	}
	
}