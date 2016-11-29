package models.dao;

import java.util.ArrayList;


import models.entities.AssignmentProductShop;
import models.entities.Order;
import models.entities.OrderProduct;
import models.entities.Product;
import models.entities.Shop;
import models.entities.State;
import models.entities.User;
import models.exceptions.ErrorAssignmentProductShopNotFound;
import models.exceptions.ErrorOrderNotFound;

public class AdminManager {
	
	private ArrayList<Order> orders;
	private ArrayList<AssignmentProductShop> assignmentsProductsShopList;
	private ArrayList<Product> listProducts;
	private ArrayList<Shop> listShop;
	
	
	public AdminManager() {
		orders = new ArrayList<>();
		assignmentsProductsShopList = new ArrayList<>();
		listProducts = new ArrayList<>();
		listShop = new ArrayList<>();
		
	}
	public static Shop createShop(String name, String srcImg){
		return new Shop(name, srcImg);
	}
	public static Product createProduct( String name, double price, String srcImg){
		return new Product(name, price, srcImg);
	}
	
	public static Order createOrder(int id, User user, ArrayList<OrderProduct> products, State state) {
		return new Order(id, user, products, state);
	}
	
	public static AssignmentProductShop createAssignmentProductShop(int id, Product product, Shop shop) {
		return new AssignmentProductShop(id, product, shop);
	}
	
	public  void  addShop(Shop shop){
		listShop.add(shop);
	}
	public void addProduct(Product product){
		listProducts.add(product);
	}
	public void addOrder(Order orderToAdd) {
		orders.add(orderToAdd);
	}
	
	public void addAssignmentProductShop(AssignmentProductShop assignment) {
		assignmentsProductsShopList.add(assignment);
	}
	
	public Shop delteShop(Shop shop) throws ErrorOrderNotFound{
		listShop.remove( searhShop(shop.getId()));
		return shop;
	}
	public Product deleteProduct(Product product) throws ErrorOrderNotFound{
		listProducts.remove(searhProduct(product.getId()));
		return product;
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
	
	public Shop searhShop(int id) throws ErrorOrderNotFound{
		for (Shop shop: listShop) {
			if (shop.getId() == id) {
				return shop;
			}
		}
		throw new ErrorOrderNotFound();
	}
	
	
	
	public Product searhProduct(int id) throws ErrorOrderNotFound{
		for (Product product: listProducts) {
			if (product.getId() == id) {
				return product;
			}
		}
		throw new ErrorOrderNotFound();
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
	public void editShop(Shop shopedit, Shop shopold) throws ErrorOrderNotFound{
		Shop shopFound = searhShop(shopold.getId());
		shopFound.setId(shopold.getId());
		shopFound.setName(shopedit.getName());
		shopFound.setSrcImg(shopedit.getSrcImg());

	}
	
	public void editProduct(Product productEdit, Product procutOld) throws ErrorOrderNotFound{
		Product productFound = searhProduct(procutOld.getId());
		productFound.setId(procutOld.getId());
		productFound.setName(productEdit.getName());
		productFound.setPrice(productEdit.getPrice());
		productFound.setSrcImg(productEdit.getSrcImg());
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

	public ArrayList<Product> getListProducts() {
		return listProducts;
	}

	public void setListProducts(ArrayList<Product> listProducts) {
		this.listProducts = listProducts;
	}

	public ArrayList<Shop> getListShop() {
		return listShop;
	}

	public void setListShop(ArrayList<Shop> listShop) {
		this.listShop = listShop;
	}
}