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

	private ArrayList<Order> ordersList;
	private ArrayList<AssignmentProductShop> assignmentsProductsShopList;
	private ArrayList<Product> productsList;
	private ArrayList<Shop> shopList;
	private ArrayList<User> usersLsit;

	public AdminManager() {
		ordersList = new ArrayList<>();
		assignmentsProductsShopList = new ArrayList<>();
		productsList = new ArrayList<>();
		shopList = new ArrayList<>();
		usersLsit = new ArrayList<>();
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

	public static AssignmentProductShop createAssignmentProductShop(int id, Product product, Shop shop) {
		return new AssignmentProductShop(id, product, shop);
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

	public void editShop(Shop shopEdit, Shop shopOld) throws ErrorShopNotFound {
		Shop shopFound = searhShop(shopOld.getId());
		shopFound.setId(shopOld.getId());
		shopFound.setName(shopEdit.getName());
		shopFound.setSrcImg(shopEdit.getSrcImg());
	}

	public void editProduct(Product productEdit, Product procutOld) throws ErrorOrderNotFound {
		Product productFound = searhProduct(procutOld.getId());
		productFound.setId(procutOld.getId());
		productFound.setName(productEdit.getName());
		productFound.setPrice(productEdit.getPrice());
		productFound.setSrcImg(productEdit.getSrcImg());
	}

	public void editUser(User userEdit, Product userOld) throws ErrorUserNotFound {
		User userFound = searhUser(userOld.getId());
		userFound.setId(userOld.getId());
		userFound.setName(userEdit.getName());
		userFound.setAddress(userEdit.getAddress());
		userFound.setPassword(userEdit.getPassword());
		userFound.setSourceImg(userEdit.getSourceImg());
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

	public ArrayList<Product> getListProducts() {
		return productsList;
	}

	public void setListProducts(ArrayList<Product> listProducts) {
		this.productsList = listProducts;
	}

	public ArrayList<Shop> getListShop() {
		return shopList;
	}

	public ArrayList<User> getUsersLsit() {
		return usersLsit;
	}

	public void setListShop(ArrayList<Shop> listShop) {
		this.shopList = listShop;
	}
}