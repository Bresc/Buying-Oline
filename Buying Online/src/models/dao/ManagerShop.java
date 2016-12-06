package models.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.entities.Shop;
import models.exceptions.ErrorShopNotFound;
import persistence.ReadXML;

public class ManagerShop {
	
	private ArrayList<Shop> shopList;
	
	public ManagerShop() {
		shopList = new ArrayList<>();
	}

	public ArrayList<Shop> getShopList() {
		return shopList;
	}

	public static Shop createShop(String name, String srcImg) throws ParserConfigurationException, SAXException, IOException {
		return new Shop(ReadXML.getAcutalID("shops", "list.shop") + 1, name, srcImg);
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
