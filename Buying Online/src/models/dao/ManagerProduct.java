package models.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.entities.Product;
import models.exceptions.ErrorOrderNotFound;
import persistence.ReadXML;

public class ManagerProduct {

	private ArrayList<Product> productsList;

	public ManagerProduct() {
		productsList = new ArrayList<>();
	}

	public static Product createProduct(String name, double price, String srcImg)
			throws ParserConfigurationException, SAXException, IOException {
		return new Product(ReadXML.getAcutalID("products", "list.product") + 1, name, price, srcImg);
	}

	public void addProduct(Product product) {
		productsList.add(product);
	}
	
	public void addAllProducts(ArrayList<Product> product) {
		productsList.addAll(product);
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

	public void editProduct(Product productEdit, Product productOld) {
		productsList.set(productsList.indexOf(productOld), productEdit);
	}

	public ArrayList<Product> getListProducts() {
		return productsList;
	}
}
