package controller;

/**
 * Este controlador implementa las acciones de la vista  del usuario y metodos de actualizacion de datos
 */
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
import models.dao.ManagerUser;
import models.entities.AssignmentProductShop;
import models.entities.Shop;
import models.exceptions.ErrorShopNotFound;
import persistence.ReadXML;
import view.user.MainWindowUser;

public class ControllerUser implements ActionListener, KeyListener, ChangeListener {

	private MainWindowUser mainWindowUser;
	private ManagerAsingProduct managerAsingProduct;
	private GeneralController generalController;
	private ManagerShop managerShop;
	private ManagerUser managerUser;

	public ControllerUser(GeneralController generalController, ManagerShop managerShop, ManagerAsingProduct managerAsingProduct) {
		this.generalController = generalController;
		this.managerShop = managerShop;
		managerUser = new ManagerUser();
		mainWindowUser = new MainWindowUser(this);
		this.managerAsingProduct = managerAsingProduct;
	}
	
	public ManagerUser getManagerUser(){
		return managerUser;
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
					managerAsingProduct.getProductsListFromShop(managerShop.searhShop(Integer.parseInt(id))));
		} catch (NumberFormatException | ErrorShopNotFound e) {
			//TODO mostrar exepcion
		}
	}

	public void changeToProductsFromShopPanel(ActionEvent e) {
		JButton bntSource = (JButton) (e.getSource());
		try {
			Shop selectedShop = managerShop.searhShop(Integer.parseInt(bntSource.getName()));
			mainWindowUser.changeToProductsFromShopPanel(managerAsingProduct.getProductsListFromShop(selectedShop));
		} catch (NumberFormatException | ErrorShopNotFound e1) {
			e1.printStackTrace();
		}
	}

	private void refreshShopData(ArrayList<Shop> shops){
		managerShop.addAllShop(shops);
	}
	
	private void refreshDataAssigmentProductShop(ArrayList<AssignmentProductShop> readAssignmentProductShops) {
		managerAsingProduct.addAllAssignmentProductShop(readAssignmentProductShops);
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