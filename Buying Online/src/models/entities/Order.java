package models.entities;

import java.util.ArrayList;

public class Order {
	private int id;
	private User user;
	private State state;
	private ArrayList<OrderProduct> products;
	public static int ID_BASE = 0;

	public Order(User user, ArrayList<OrderProduct> products, State state) {
		this.id = ID_BASE++;
		this.user = user;
		this.products = products;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<OrderProduct> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<OrderProduct> orderProducts) {
		this.products = orderProducts;
	}

	public State getState() {
		return state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setState(State state) {
		this.state = state;
	}
}