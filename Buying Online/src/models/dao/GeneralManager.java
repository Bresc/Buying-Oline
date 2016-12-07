package models.dao;

import models.entities.Shop;
import models.entities.User;
import models.exceptions.ErrorShopNotFound;
import models.exceptions.ErrorUserNotFound;

public class GeneralManager {

	private ManagerUser user;
	private ManagerShop shop;
	public static final String USER = "user";
	public static final String ADMIN = "admin";
	public static final String SHOP = "shop";

	public GeneralManager() {
		user = new ManagerUser();
		shop = new ManagerShop();
	}

	public String confirmTheLoginUser(String nickName, String password) {
		String valie = ADMIN;
		for (User use : user.getUsersList()) {
			if (use.getName().equals(nickName) && use.getPassword().equals(password)) {
				return valie = USER;
			}
		}

		for (Shop sho : shop.getListShop()) {
			if (sho.getName().equalsIgnoreCase(nickName)) {
				return valie = SHOP;
			}
		}
		System.out.println(shop.getListShop());
		System.out.println(user.getUsersList());
		return valie;
	}

	public User searchUser(String name, String password) throws ErrorUserNotFound {
		for (User use : user.getUsersList()) {
			if (use.getName().equals(name) && use.getPassword().equals(password)) {
				return use;
			}
		}
		throw new ErrorUserNotFound();
	}

	public Shop searchTheShop(String name) throws ErrorShopNotFound {
		for (Shop sho : shop.getListShop()) {
			if (sho.getName().equalsIgnoreCase(name)) {
				return sho;
			}
		}
		throw new ErrorShopNotFound();
	}
}