package models.dao;

import java.util.ArrayList;

import exceptions.ErrorAssignmentProductShopNotFound;
import exceptions.ErrorOrderNotFound;
import models.entities.AssignmentProductShop;
import models.entities.Order;
import models.entities.OrderProduct;
import models.entities.Product;
import models.entities.Shop;
import models.entities.State;
import models.entities.User;

public class AdminManager {
	
	private ArrayList<Order> orders;
	private ArrayList<AssignmentProductShop> assignmentsProductsShopList;
	
	public AdminManager() {
		orders = new ArrayList<>();
		assignmentsProductsShopList = new ArrayList<>();
	}
	
	public static Order createOrder(int id, User user, ArrayList<OrderProduct> products, State state) {
		return new Order(id, user, products, state);
	}
	
	public static AssignmentProductShop createAssignmentProductShop(int id, Product product, Shop shop) {
		return new AssignmentProductShop(id, product, shop);
	}
	
	public void addOrder(Order orderToAdd) {
		orders.add(orderToAdd);
	}
	
	public void addAssignmentProductShop(AssignmentProductShop assignment) {
		assignmentsProductsShopList.add(assignment);
	}
	
	public Order deleteOrder(Order orderToDelete) throws ErrorOrderNotFound{
		orders.remove(searhOrder(orderToDelete.getId()));
		return orderToDelete;
	}
	
	public AssignmentProductShop deleteAssignmentProductShop(AssignmentProductShop assignmentToDelete)
			throws ErrorAssignmentProductShopNotFound {
		assignmentsProductsShopList.remove(searchAssignmentProductShop(assignmentToDelete.getId()));
		return assignmentToDelete;
	}
	
	public Order searhOrder(int id) throws ErrorOrderNotFound{
		for (Order order : orders) {
			if (order.getId() == id) {
				return order;
			}
		}
		throw new ErrorOrderNotFound();
	}
	
	public AssignmentProductShop searchAssignmentProductShop(int id) throws ErrorAssignmentProductShopNotFound {
		for (AssignmentProductShop assignmentProductShop : assignmentsProductsShopList) {
			if (assignmentProductShop.getId() == id) {
				return assignmentProductShop;
			}
		}
		throw new ErrorAssignmentProductShopNotFound();
	}
	
	public void editOrder(Order newOrder, Order oldOrder) throws ErrorOrderNotFound{
		Order orderFound = searhOrder(oldOrder.getId());
		orderFound.setId(newOrder.getId());
		orderFound.setState(newOrder.getState());
		orderFound.setProducts(newOrder.getProducts());
	}
	
	public void editAssignmentProductShop(AssignmentProductShop newAssignment, AssignmentProductShop oldAssignment) 
			throws ErrorAssignmentProductShopNotFound {
		AssignmentProductShop assignmentFound = searchAssignmentProductShop(oldAssignment.getId());
		assignmentFound.setId(newAssignment.getId());
		assignmentFound.setProduct(newAssignment.getProduct());
		assignmentFound.setshop(newAssignment.getShop());
	}
}