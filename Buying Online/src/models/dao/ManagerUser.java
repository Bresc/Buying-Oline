package models.dao;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.entities.User;
import models.exceptions.ErrorUserNotFound;
import persistence.ReadXML;

public class ManagerUser {

	private ArrayList<User> usersList;

	public ManagerUser() {
		usersList = new ArrayList<>();
	}

	public static User createUser(String name, String address, String password, String sourceImg) throws ParserConfigurationException, SAXException, IOException {
		return new User(ReadXML.getAcutalID("users", "list.user") + 1, name, address, password, sourceImg);
	}

	public void addUser(User user) {
		usersList.add(user);
	}

	public void addAllUser(ArrayList<User> user) {
		usersList.addAll(user);
	}
	
	public void clearList() {
		usersList.clear();
	}
	
	public User deleteUser(User user) throws ErrorUserNotFound {
		usersList.remove(searhUser(user.getId()));
		return user;
	}

	public User searhUser(int id) throws ErrorUserNotFound {
		for (User user : usersList) {
			if (user.getId() == id) {
				return user;
			}
		}
		throw new ErrorUserNotFound();
	}

	public User validateUserLogin(String name, String password) throws ErrorUserNotFound {
		for (User user : usersList) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				return user;
			}
		}
		throw new ErrorUserNotFound();
	}

	public User searchUserNamePassword(String name, String password) throws ErrorUserNotFound {
		for (User user : usersList) {
			if (user.getName().equals(name) && user.getPassword().equals(password)) {
				return user;
			}
		}
		throw new ErrorUserNotFound();
	}

	public void editUser(User userEdit, User userOld) throws ErrorUserNotFound {
		usersList.set(usersList.indexOf(userOld), userEdit);
	}

	public ArrayList<User> getUsersList() {
		return usersList;
	}

	
}
