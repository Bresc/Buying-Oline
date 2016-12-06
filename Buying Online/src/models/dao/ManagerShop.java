package models.dao;

import java.util.ArrayList;

import models.entities.Shop;
import models.exceptions.ErrorShopNotFound;

public class ManagerShop {
	
	private ArrayList<Shop> shopList;
	
	public ManagerShop() {
		shopList = new ArrayList<>();
	}

	public ArrayList<Shop> getShopList() {
		return shopList;
	}

	public static Shop createShop(String name, String srcImg) {
		return new Shop( name, srcImg);
	}
	
	public void addShop(Shop shop) {
		shopList.add(shop);
	}
	
	public Shop delteShop(Shop shop) throws ErrorShopNotFound {
		shopList.remove(searhShop(shop.getId()));
		return shop;
	}
	
	public Shop searhShop(int id) throws ErrorShopNotFound {
		for (Shop shop : shopList) {
			if (shop.getId() == id) {
				return shop;
			}
		}
		throw new ErrorShopNotFound();
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
	
	public ArrayList<Shop> getListShop() {
		return shopList;
	}
	
	public void setListShop(ArrayList<Shop> listShop) {
		this.shopList = listShop;
	}
}
