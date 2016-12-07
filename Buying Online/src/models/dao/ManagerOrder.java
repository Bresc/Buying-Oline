package models.dao;

import java.util.ArrayList;

import models.entities.Order;
import models.entities.OrderProduct;
import models.entities.State;
import models.entities.User;
import models.exceptions.ErrorOrderNotFound;

public class ManagerOrder {

	private ArrayList<Order> ordersList;

	public ManagerOrder() {
		ordersList = new ArrayList<>();
	}

	public static Order createOrder(User user, ArrayList<OrderProduct> products, State state) {
		return new Order(user, products, state);
	}

	public void addOrder(Order orderToAdd) {
		ordersList.add(orderToAdd);
	}

	public Order deleteOrder(Order orderToDelete) throws ErrorOrderNotFound {
		ordersList.remove(searhOrder(orderToDelete.getId()));
		return orderToDelete;
	}

	public Order searhOrder(int id) throws ErrorOrderNotFound {
		for (Order order : ordersList) {
			if (order.getId() == id) {
				return order;
			}
		}
		throw new ErrorOrderNotFound();
	}

	public void editOrder(Order newOrder, Order oldOrder) throws ErrorOrderNotFound {
		ordersList.set(ordersList.indexOf(oldOrder), newOrder);
	}
}
