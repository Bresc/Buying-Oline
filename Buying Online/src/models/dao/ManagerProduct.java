package models.dao;

import java.util.ArrayList;

import models.entities.Product;
import models.exceptions.ErrorOrderNotFound;

public class ManagerProduct {

	private ArrayList<Product> productsList;
	
	public ManagerProduct() {
		productsList = new ArrayList<>();
	}

	public static Product createProduct(String name, double price, String srcImg) {
		return new Product( name, price, srcImg);
	}
	
	public void addProduct(Product product) {
		productsList.add(product);
	}
	
	public Product deleteProduct(Product product) throws ErrorOrderNotFound {
		productsList.remove(searhProduct(product.getId()));
		return product;
	}
	
	public Product searhProduct(int id) throws ErrorOrderNotFound {
		for (Product product : productsList) {
			if (product.getId() == id) {
				return product;
			}
		}
		throw new ErrorOrderNotFound();
	}
	
	public void editProduct(Product productEdit, Product productOld){
		productsList.set(productsList.indexOf(productOld), productEdit);
		
	}
	public ArrayList<Product> getListProducts() {
		return productsList;
	}
}
