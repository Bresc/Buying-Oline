package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import models.dao.ManagerAsingProduct;
import models.dao.ManagerShop;
import models.entities.AssignmentProductShop;
import models.entities.Shop;
import models.exceptions.ErrorShopNotFound;
import persistence.ReadXML;
import view.user.MainWindowUser;

public class ControllerUser implements ActionListener, KeyListener, ChangeListener {

	private MainWindowUser mainWindowUser;
	private ManagerShop managerShop;
	private ManagerAsingProduct adminManagerAssingProduct;
	private GeneralController generalController;

	public ControllerUser(GeneralController generalController) {
		this.generalController = generalController;
		mainWindowUser = new MainWindowUser(this);
		managerShop = new ManagerShop();
		adminManagerAssingProduct = new ManagerAsingProduct();
	}

	@Override
	public void actionPerformed(ActionEvent arg2) {
		switch (ActionsUser.valueOf(arg2.getActionCommand())) {
		case BACK_TO_PANEL_RESTAURANTS:
			mainWindowUser.refreshShopList(managerShop.getListShop(), this);
			break;
		case LOG_OUT:
			logOut();
			break;
		case SHOW_PRODUCTS_BY_SHOP:
			showProductsByShop(((JButton) arg2.getSource()).getName());
			break;
		}
	}
	
	public void setVisible() {
		refreshShopData();
		mainWindowUser.refreshShopList(managerShop.getListShop(), this);
		mainWindowUser.setVisible(true);
	}

	private void refreshShopData() {
		try {
			refreshShopData(ReadXML.readShop());
			refreshDataAssigmentProductShop(ReadXML.readAsigmentProducts());
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO poner Exepciones
		}
	}
	
	private void logOut() {
		mainWindowUser.setVisible(false);
		generalController.LoginVisible();
	}

	private void showProductsByShop(String id) {
		try {
			mainWindowUser.changeToProductsFromShopPanel(
					adminManagerAssingProduct.getProductsListFromShop(managerShop.searhShop(Integer.parseInt(id))));
		} catch (NumberFormatException | ErrorShopNotFound e) {
			//TODO mostrar exepcion
		}
	}

	public void changeToProductsFromShopPanel(ActionEvent e) {
		JButton bntSource = (JButton) (e.getSource());
		try {
			Shop selectedShop = managerShop.searhShop(Integer.parseInt(bntSource.getName()));
			mainWindowUser.changeToProductsFromShopPanel(adminManagerAssingProduct.getProductsListFromShop(selectedShop));
		} catch (NumberFormatException | ErrorShopNotFound e1) {
			e1.printStackTrace();
		}
	}

	private void refreshShopData(ArrayList<Shop> shops){
		managerShop.addAllShop(shops);
	}
	
	private void refreshDataAssigmentProductShop(ArrayList<AssignmentProductShop> readAssignmentProductShops) {
		adminManagerAssingProduct.addAllAssignmentProductShop(readAssignmentProductShops);
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}