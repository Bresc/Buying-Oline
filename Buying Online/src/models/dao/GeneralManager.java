package models.dao;

import models.entities.Shop;
import models.entities.User;
import models.exceptions.ErrorShopNotFound;
import models.exceptions.ErrorUserNotFound;

public class GeneralManager {

	
	public static final String USER = "user";
	public static final String ADMIN = "admin";
	public static final String SHOP = "shop";
	
	private ManagerShop managerShop;
	private ManagerUser managerUser;

	public GeneralManager(ManagerUser managerUser, ManagerShop managerShop) {
		this.managerShop = managerShop;
		this.managerUser = managerUser;
	}

	public String confirmTheLoginUser(String nickName, String password) {
		String valie = ADMIN;
		for (User use : managerUser.getUsersList()) {
			if (use.getName().equals(nickName) && use.getPassword().equals(password)) {
				return valie = USER;
			}
		}

		for (Shop sho : managerShop.getListShop()) {
			if (sho.getName().equalsIgnoreCase(nickName)) {
				return valie = SHOP;
			}
		}
		return valie;
	}

	public User searchUser(String name, String password) throws ErrorUserNotFound {
		for (User use : managerUser.getUsersList()) {
			if (use.getName().equals(name) && use.getPassword().equals(password)) {
				return use;
			}
		}
		throw new ErrorUserNotFound();
	}

	public Shop searchTheShop(String name) throws ErrorShopNotFound {
		for (Shop sho : managerShop.getListShop()) {
			if (sho.getName().equalsIgnoreCase(name)) {
				return sho;
			}
		}
		throw new ErrorShopNotFound();
	}
}