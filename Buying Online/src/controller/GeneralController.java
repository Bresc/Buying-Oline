package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.dao.GeneralManager;
import models.exceptions.ErrorShopNotFound;
import models.exceptions.ErrorUserNotFound;
import view.login.DialogLogIn;

public class GeneralController implements ActionListener {

	private DialogLogIn log;
	private GeneralManager general;
	private ControllerUser userStart;
	private ControllerShop shopStart;
	private ControllerAdmin adminStart;
	public static final String USER = "user";
	public static final String SHOP = "shop";
	public static final String ADMIN = "admin";

	public GeneralController() {
		log = new DialogLogIn(this);
		general = new GeneralManager();
		userStart = new ControllerUser();
		shopStart = new ControllerShop();
		adminStart = new ControllerAdmin();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (GeneralActions.valueOf(e.getActionCommand())) {
		case CONFIRM:
			confirm();
			break;
		case OPEN_DIALOG_CHOOSE:
			openDialog();
			break;
		default:
			break;
		}
	}

	public ControllerUser getManA() {
		return userStart;
	}

	private void openDialog() {
		// TODO Auto-generated method stub

	}

	private void confirm() {

		if (general.confirmTheLoginUser(log.getTheName(), log.getPassword()).equals(USER)) {
			userStart.setVi();
			log.setVisible(false);
			System.out.println("Holi");
			try {
				general.searchUser(log.getTheName(), log.getPassword());
			} catch (ErrorUserNotFound e) {
				new JOptionPane("Hola", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} else if (general.confirmTheLoginUser(log.getTheName(), log.getPassword()).equals(SHOP)) {
			shopStart.setVi();
			log.setVisible(false);
			System.out.println("Holi2");
			try {
				general.searchTheShop(log.getTheName());
			} catch (ErrorShopNotFound e) {
				new JOptionPane("Hola", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		} else {
			adminStart.setVi();
			log.setVisible(false);
			System.out.println("Holi3");
		}
	}

	public void run() {
		log.setVisible(true);
	}
}