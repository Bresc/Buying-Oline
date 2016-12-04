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
	public static int ACTUAL_ID_SHOP;
	public static int ACTUAL_ID_PRODUCT;

	private ArrayList<Order> ordersList;
	private ArrayList<AssignmentProductShop> assignmentsProductsShopList;
	private ArrayList<Product> productsList;
	private ArrayList<Shop> shopList;
	private ArrayList<User> usersList;
	private ArrayList<OrderProduct> listOrderProduct;

	public AdminManager() {
		listOrderProduct = new ArrayList<>();
		ordersList = new ArrayList<>();
		assignmentsProductsShopList = new ArrayList<>();
		productsList = new ArrayList<>();
		shopList = new ArrayList<>();
		usersList = new ArrayList<>();
	}
	public static OrderProduct CreateOrderProduct(Product product ,int quantity){
		return new OrderProduct(product, quantity);
	}
	public static User createUser(String name, String address, String password, String sourceImg) {
		return new User(name, address, password, sourceImg);
	}

	public static Shop createShop(String name, String srcImg) {
		return new Shop(ACTUAL_ID_SHOP, name, srcImg);
	}

	public static Product createProduct(String name, double price, String srcImg) {
		return new Product(ACTUAL_ID_PRODUCT, name, price, srcImg);
	}

	public static Order createOrder( User user, ArrayList<OrderProduct> products, State state) {
		return new Order( user, products, state);
	}

	public static AssignmentProductShop createAssignmentProductShop(Product product, Shop shop) {
		return new AssignmentProductShop(product, shop);
	}
	//Metodos para colocar y sumar id de tienda y producto
	public static void updateActualIdShop(int idActual){
		ACTUAL_ID_SHOP = idActual;
	}
	
	public static void updateActualIdProduct(int idActual){
		ACTUAL_ID_PRODUCT = idActual;
	}
	
	public void summIdShop(){
		ACTUAL_ID_SHOP++;
	}
	
	public void summIdProduct(){
		ACTUAL_ID_PRODUCT++;
	}
	
	public void addOrderProduct(OrderProduct orderProduct){
		listOrderProduct.add(orderProduct );
	}
	
	public void addUser(User user) {
		usersList.add(user);
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
		usersList.remove(searhUser(user.getId()));
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
		for (User user : usersList) {
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
		for (User user : usersList) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				helper = true;
			}
		}
		return helper;
	}
	
	/**
	 * Este metodo es para diferenciar un administrador de un usuario 
	 * y a su vez lo diferencia de una tienda 
	 * @param name nombre a buscar
	 * @param password contraseña a buscar
	 * @return retorna user si encuetra en la lista de usuario, 
	 * shop si lo encuentra en la de tiendas,
	 * o admin si no lo encuentra en ninguna de las dos
	 */
	public String searchForLogIn(String name, String password){
		String helperWho = "admin";
		for (User user : usersList) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				helperWho = "user";
			}
		}
		
		for (Shop shop : shopList) {
			if (shop.getName().equals(name)) {
				helperWho = "shop";
			}
		}
		
		return helperWho;
	}
	
	public User searchUserNamePassword(String name, String password) throws ErrorUserNotFound{
		for (User user : usersList) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				return user;
			}
		}
		throw new ErrorUserNotFound();
	}
	
	public Shop searchShopName(String name) throws ErrorShopNotFound{
		for (Shop shop : shopList) {
			if (shop.getName().equalsIgnoreCase(name)) {
				 return shop;
			}
		}
		throw new ErrorShopNotFound();
	}
	
	public void editShop(Shop shopEdit, Shop shopOld) throws ErrorShopNotFound {
		shopList.set(shopList.indexOf(shopOld), shopEdit);
	}

	public void editProduct(Product productEdit, Product productOld){
		productsList.set(productsList.indexOf(productOld), productEdit);
	}

	public void editUser(User userEdit, User userOld) throws ErrorUserNotFound {
		usersList.set(usersList.indexOf(userOld), userEdit);
	}

	public void editOrder(Order newOrder, Order oldOrder) throws ErrorOrderNotFound {
		ordersList.set(ordersList.indexOf(oldOrder), newOrder);
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
		return usersList;
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
			return usersList;
		}else {
			return productsList;
		}
	}
}