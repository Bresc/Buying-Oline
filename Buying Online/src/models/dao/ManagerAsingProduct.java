package models.dao;

import java.util.ArrayList;

import models.entities.AssignmentProductShop;
import models.entities.Product;
import models.entities.Shop;
import models.exceptions.ErrorAssignmentProductShopNotFound;

public class ManagerAsingProduct {

	public static final int PAGE_SIZE = 10;

	
	private ArrayList<AssignmentProductShop> assignmentsProductsShopList;

	public ManagerAsingProduct() {

		assignmentsProductsShopList = new ArrayList<>();
	}

	public static AssignmentProductShop createAssignmentProductShop(Product product, Shop shop) {
		return new AssignmentProductShop(product, shop);
	}

	public void addAssignmentProductShop(AssignmentProductShop assignment) {
		assignmentsProductsShopList.add(assignment);
	}

	public void addAllAssignmentProductShop(ArrayList<AssignmentProductShop> assignment) {
		assignmentsProductsShopList.addAll(assignment);
	}
	
	public void clearList() {
		assignmentsProductsShopList.clear();
	}

	public AssignmentProductShop deleteAssignmentProductShop(AssignmentProductShop assignmentToDelete)
			throws ErrorAssignmentProductShopNotFound {
		assignmentsProductsShopList.remove(searchAssignmentProductShop(assignmentToDelete.getId()));
		return assignmentToDelete;
	}

	public AssignmentProductShop searchAssignmentProductShop(int id) throws ErrorAssignmentProductShopNotFound {
		for (AssignmentProductShop assignmentProductShop : assignmentsProductsShopList) {
			if (assignmentProductShop.getId() == id) {
				return assignmentProductShop;
			}
		}
		throw new ErrorAssignmentProductShopNotFound();
	}

	public void editAssignmentProductShop(AssignmentProductShop newAssignment, AssignmentProductShop oldAssignment)
			throws ErrorAssignmentProductShopNotFound {
		AssignmentProductShop assignmentFound = searchAssignmentProductShop(oldAssignment.getId());
		assignmentFound.setId(newAssignment.getId());
		assignmentFound.setProduct(newAssignment.getProduct());
		assignmentFound.setshop(newAssignment.getShop());
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

	public ArrayList<AssignmentProductShop> getAssignmentsProductsShopList() {
		return assignmentsProductsShopList;
	}
}