package controller;

/*
 * Este controllador  tiene las acciones de registrar le logear y tuliza las acciones de el enumerado de acciones generales 
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.dao.GeneralManager;
import models.dao.ManagerAsingProduct;
import models.exceptions.ErrorUserNotFound;
import view.login.DialogChooseWhoYouAre;
import view.login.DialogLogin;

public class GeneralController implements ActionListener {

	private DialogLogin loginMainWindow;
	private DialogChooseWhoYouAre chooseWhoYouAre;
	private ControllerUser controllerUser;
	private ControllerShop controllerShop;
	private ControllerAdmin controllerAdmin;
	private ManagerAsingProduct managerAsingProduct;   
	public static final String USER = "user";
	public static final String SHOP = "shop";
	public static final String ADMIN = "admin";

	public GeneralController() {
		loginMainWindow = new DialogLogin(this);
		controllerShop = new ControllerShop(this);
		managerAsingProduct = new ManagerAsingProduct();
		controllerUser = new ControllerUser(this, controllerShop.getManagerShop(), managerAsingProduct);
		controllerAdmin = new ControllerAdmin(this, controllerShop.getManagerShop(), controllerUser.getManagerUser(), managerAsingProduct);
		chooseWhoYouAre = new DialogChooseWhoYouAre(this);
		readAllData();
	}

	private void readAllData() {
		try {
			controllerAdmin.refrehAllData();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
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
			chooseWhoYouAre.setVisible(false);
			controllerAdmin.showAddUserDialog();
			break;
		default:
			break;
		}
	}

	private void validateUserFromLogin() {
		String userName = loginMainWindow.getUsername(), password = loginMainWindow.getPassword();
		if (validateLoginUser(userName, password)) {
			loginMainWindow.setVisible(false);
			controllerUser.setVisible();
		}else if (validateLoginShop(userName, password)) {
			loginMainWindow.setVisible(false);
		}else {
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
//			TODO: mostrar error
			return false;
		}
	}
	
	private boolean validateLoginShop(String name, String password) {
		try {
			controllerAdmin.validateUser(name, password);
			return true;
		} catch (ErrorUserNotFound e) {
//			TODO: mostrar error
			return false;
		}
	}

	private void openDialog() {
		chooseWhoYouAre.setVisible(true);
		loginMainWindow.setVisible(false);
	}

	// private void VALIDATE_USER_FROM_LOGIN() {
	// if (general.confirmTheLoginUser(loginMainWindow.getTheName(),
	// loginMainWindow.getPassword()).equals(USER)) {
	// userStart.setVi();
	// loginMainWindow.setVisible(false);
	// System.out.println("Holi");
	// try {
	// general.searchUser(loginMainWindow.getTheName(),
	// loginMainWindow.getPassword());
	// } catch (ErrorUserNotFound e) {
	// new JOptionPane("Hola", JOptionPane.ERROR_MESSAGE);
	// e.printStackTrace();
	// }
	// } else if (general.confirmTheLoginUser(loginMainWindow.getTheName(),
	// loginMainWindow.getPassword()).equals(SHOP)) {
	// shopStart.setVi();
	// loginMainWindow.setVisible(false);
	// System.out.println("Holi2");
	// try {
	// general.searchTheShop(loginMainWindow.getTheName());
	// } catch (ErrorShopNotFound e) {
	// new JOptionPane("Hola", JOptionPane.ERROR_MESSAGE);
	// e.printStackTrace();
	// }
	// } else {
	// adminStart.setVi();
	// loginMainWindow.setVisible(false);
	// System.out.println("Holi3");
	// }
	// }

	public void run() {
		loginMainWindow.setVisible(true);
	}

	public void LoginVisible() {
		loginMainWindow.clearLoginDialog();
		loginMainWindow.setVisible(true);
	}
}