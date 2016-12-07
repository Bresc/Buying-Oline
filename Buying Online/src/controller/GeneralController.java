package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.dao.GeneralManager;
import models.entities.User;
import models.exceptions.ErrorShopNotFound;
import models.exceptions.ErrorUserNotFound;
import persistence.ReadXML;
import view.login.DialogLogin;

public class GeneralController implements ActionListener {

	private DialogLogin loginMainWindow;
	private GeneralManager general;
	private ControllerUser userStart;
	private ControllerShop shopStart;
	private ControllerAdmin controllerAdmin;
	public static final String USER = "user";
	public static final String SHOP = "shop";
	public static final String ADMIN = "admin";

	public GeneralController() {
		loginMainWindow = new DialogLogin(this);
		controllerAdmin = new ControllerAdmin();
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
		}
	}

	private void validateUserFromLogin() {
		String userName = loginMainWindow.getName(), password = loginMainWindow.getPassword();
		if (validateLoginUser(userName, password)) {
//			TODO: Controlador user
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

	private void openDialog() {

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
}