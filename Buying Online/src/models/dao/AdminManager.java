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
import models.exceptions.ErrorShopNotFound;
import models.exceptions.ErrorUserNotFound;

public class AdminManager {
	
	public static final int PAGE_SIZE = 10;

	private ArrayList<Order> ordersList;
	private ArrayList<AssignmentProductShop> assignmentsProductsShopList;
	private ArrayList<Product> productsList;
	private ArrayList<Shop> shopList;
	private ArrayList<User> usersLsit;
	private ArrayList<OrderProduct> listOrderProduct;

	public AdminManager() {
		listOrderProduct = new ArrayList<>();
		ordersList = new ArrayList<>();
		assignmentsProductsShopList = new ArrayList<>();
		productsList = new ArrayList<>();
		shopList = new ArrayList<>();
		usersLsit = new ArrayList<>();
	}
	public static OrderProduct CreateOrderProduct(Product product ,int quantity){
		return new OrderProduct(product, quantity);
	}
	public static User createUser(String name, String address, String password, String sourceImg) {
		return new User(name, address, password, sourceImg);
	}

	public static Shop createShop(String name, String srcImg) {
		return new Shop(name, srcImg);
	}

	public static Product createProduct(String name, double price, String srcImg) {
		return new Product(name, price, srcImg);
	}

	public static Order createOrder(int id, User user, ArrayList<OrderProduct> products, State state) {
		return new Order(id, user, products, state);
	}

	public static AssignmentProductShop createAssignmentProductShop(Product product, Shop shop) {
		return new AssignmentProductShop(product, shop);
	}
	public void addOrderProduct(OrderProduct orderProduct){
		listOrderProduct.add(orderProduct );
	}
	
	public void addUser(User user) {
		usersLsit.add(user);
	}

	public void addShop(Shop shop) {
		shopList.add(shop);
	}

	public void addProduct(Product product) {
		productsList.add(product);
	}

	public void addOrder(Order orderToAdd) {
		ordersList.add(orderToAdd);
	}

	public void addAssignmentProductShop(AssignmentProductShop assignment) {
		assignmentsProductsShopList.add(assignment);
	}

	public User deleteUser(User user) throws ErrorUserNotFound {
		usersLsit.remove(searhUser(user.getId()));
		return user;
	}

	public Shop delteShop(Shop shop) throws ErrorShopNotFound {
		shopList.remove(searhShop(shop.getId()));
		return shop;
	}

	public Product deleteProduct(Product product) throws ErrorOrderNotFound {
		productsList.remove(searhProduct(product.getId()));
		return product;
	}

	public Order deleteOrder(Order orderToDelete) throws ErrorOrderNotFound {
		ordersList.remove(searhOrder(orderToDelete.getId()));
		return orderToDelete;
	}

	public AssignmentProductShop deleteAssignmentProductShop(AssignmentProductShop assignmentToDelete)
			throws ErrorAssignmentProductShopNotFound {
		assignmentsProductsShopList.remove(searchAssignmentProductShop(assignmentToDelete.getId()));
		return assignmentToDelete;
	}

	public Shop searhShop(int id) throws ErrorShopNotFound {
		for (Shop shop : shopList) {
			if (shop.getId() == id) {
				return shop;
			}
		}
		throw new ErrorShopNotFound();
	}

	public Product searhProduct(int id) throws ErrorOrderNotFound {
		for (Product product : productsList) {
			if (product.getId() == id) {
				return product;
			}
		}
		throw new ErrorOrderNotFound();
	}

	public User searhUser(int id) throws ErrorUserNotFound {
		for (User user : usersLsit) {
			if (user.getId() == id) {
				return user;
			}
		}
		throw new ErrorUserNotFound();
	}

	public Order searhOrder(int id) throws ErrorOrderNotFound {
		for (Order order : ordersList) {
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

	public boolean searchForLogInUser(String name, String password){
		boolean helper = false;
		for (User user : usersLsit) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				helper = true;
			}
		}
		return helper;
	}
	
	public void editShop(Shop shopEdit, Shop shopOld) throws ErrorShopNotFound {
		shopList.set(shopOld.getId(), shopEdit);
	}

	public void editProduct(Product productEdit, Product procutOld){
		procutOld.setName(productEdit.getName());
		procutOld.setPrice(productEdit.getPrice());
		procutOld.setSrcImg(productEdit.getSrcImg());
	}

	public void editUser(User userEdit, User userOld) throws ErrorUserNotFound {
		userOld.setName(userEdit.getName());
		userOld.setAddress(userEdit.getAddress());
		userOld.setPassword(userEdit.getPassword());
		userOld.setSourceImg(userEdit.getSourceImg());
	}

	public void editOrder(Order newOrder, Order oldOrder) throws ErrorOrderNotFound {
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
	
	public double getTotalPriceOrder(){
		double total = 0;
		for (OrderProduct orderProduct : listOrderProduct) {
			total = (orderProduct.getQuantity()* orderProduct.getProduct().getPrice());
		}
		return total;
	}
	
	public ArrayList<Product> getProductsListFromShop(Shop shop) {
		ArrayList<Product> productsFromShop = new ArrayList<>();
		for (AssignmentProductShop assignmentProductShop : assignmentsProductsShopList) {
			if (assignmentProductShop.getShop().getId() == shop.getId()) {
				productsFromShop.add(assignmentProductShop.getProduct());
			}
		}
		return productsFromShop;
	}

	public ArrayList<Product> getListProducts() {
		return productsList;
	}

	public void setListProducts(ArrayList<Product> listProducts) {
		this.productsList = listProducts;
	}

	public ArrayList<Shop> getListShop() {
		return shopList;
	}
	
	public ArrayList<AssignmentProductShop> getAssignmentsProductsShopList() {
		return assignmentsProductsShopList;
	}
	public ArrayList<User> getUsersList() {
		return usersLsit;
	}

	public void setListShop(ArrayList<Shop> listShop) {
		this.shopList = listShop;
	}
	
	///Paginacion
	
	public ArrayList<?> paginate(ArrayList<?> list, int page){
		int firstElement = (page - 1) * PAGE_SIZE;
		int lastElement = (page * PAGE_SIZE);
		lastElement = lastElement > list.size() ? list.size() : lastElement;
		return new ArrayList<>(list.subList(firstElement, lastElement));
	}
	
	public int getTotalPages(ArrayList<?> list){
		int totalPages = list.size() / PAGE_SIZE;
		return (totalPages % PAGE_SIZE) > 0 ? ++totalPages : totalPages;
	}
	
	public ArrayList<?> returnListDependIndex(int index){
		if (index == 0) {
			return shopList;
		}else if (index == 1) {
			return usersLsit;
		}else {
			return productsList;
		}
	}
}