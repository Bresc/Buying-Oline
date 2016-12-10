package controller;

/**
 * Este es el controlador principal, se encarga de unir los 
 * demas controladores, y valilda que tipo de usuario es Ej: Admin, User, Shop
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.dao.ManagerAsingProduct;
import models.exceptions.ErrorShopNotFound;
import models.exceptions.ErrorUserNotFound;
import view.login.DialogChooseWhoYouAre;
import view.login.LoginMainWindow;

public class GeneralController implements ActionListener {

	private LoginMainWindow loginMainWindow;
	private DialogChooseWhoYouAre chooseWhoYouAre;
	private ControllerUser controllerUser;
	private ControllerShop controllerShop;
	private ControllerAdmin controllerAdmin;
	private ManagerAsingProduct managerAsingProduct;
	public static final String USER = "user";
	public static final String SHOP = "shop";
	public static final String ADMIN = "admin";

	public GeneralController() {
		loginMainWindow = new LoginMainWindow(this);
		controllerShop = new ControllerShop(this, loginMainWindow);
		managerAsingProduct = new ManagerAsingProduct();
		controllerUser = new ControllerUser(this, controllerShop.getManagerShop(), managerAsingProduct);
		controllerAdmin = new ControllerAdmin(this, controllerShop.getManagerShop(), controllerUser.getManagerUser(),
				managerAsingProduct, loginMainWindow);
		readAllData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (GeneralActions.valueOf(e.getActionCommand())) {
		case VALIDATE_USER_FROM_LOGIN:
			validateUserFromLogin();
			break;
		case SHOW_REGISTER_DIALOG:
			openDialog();
			break;
		case SHOP_LOG_IN:
			chooseWhoYouAre.setVisible(false);
			controllerAdmin.showAddShopDialog();
			break;
		case USER_LOG_IN:
			managerLogOut();
			break;
		}
	}

	private void managerLogOut() {
		chooseWhoYouAre.setVisible(false);
		controllerAdmin.showAddUserDialog();
		loginMainWindow.clearLoginDialog();
	}

	/**
	 * Metodo que actualiza los datos en cada vista al ingresar como usuario,
	 * tienda o Administrador
	 */
	private void readAllData() {
		try {
			controllerAdmin.refrehAllData();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private void validateUserFromLogin() {
		String userName = loginMainWindow.getUsername(), password = loginMainWindow.getPassword();
		if (validateLoginUser(userName, password)) {
			loginMainWindow.setVisible(false);
			controllerUser.setVisible();
		} else if (validateLoginShop(userName)) {
			loginMainWindow.setVisible(false);
			controllerShop.setVisible();
		} else {
			loginMainWindow.setVisible(false);
			readAllData();
			controllerAdmin.setVisible();
		}
	}

	private boolean validateLoginUser(String name, String password) {
		try {
			controllerAdmin.validateUser(name, password);
			return true;
		} catch (ErrorUserNotFound e) {
			// TODO: mostrar error
			return false;
		}
	}

	private boolean validateLoginShop(String name) {
		try {
			controllerShop.setThisShop(controllerAdmin.validateShop(name));
			return true;
		} catch (ErrorShopNotFound e) {
			// TODO: mostrar error
			return false;
		}
	}

	private void openDialog() {
		chooseWhoYouAre = new DialogChooseWhoYouAre(this);
		loginMainWindow.setVisible(false);
	}

	public void run() {
		loginMainWindow.setVisible(true);
	}

	public void LoginVisible() {
		loginMainWindow.clearLoginDialog();
		loginMainWindow.setVisible(true);
	}

}