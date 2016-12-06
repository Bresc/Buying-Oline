package models.dao;

import java.util.ArrayList;

import models.entities.OrderProduct;
import models.entities.Product;

public class ManagerOrderProduct {
	
	private ArrayList<OrderProduct> listOrderProduct;
	
	public ManagerOrderProduct() {
		listOrderProduct = new ArrayList<>();
		
	}
	
	public static OrderProduct CreateOrderProduct(Product product ,int quantity){
		return new OrderProduct(product, quantity);
	}
	
	public void addOrderProduct(OrderProduct orderProduct){
		listOrderProduct.add(orderProduct );
	}

}
