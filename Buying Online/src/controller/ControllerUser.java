package controller;

/*
 * Este controlador implementa las acciones de la vista del usuario con susu respectivos metodos 
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

import models.dao.GeneralManager;
import models.dao.ManagerAsingProduct;
import models.dao.ManagerOrder;
import models.dao.ManagerOrderProduct;
import models.dao.ManagerProduct;
import models.dao.ManagerShop;
import models.dao.ManagerUser;
import models.entities.AssignmentProductShop;
import models.entities.Order;
import models.entities.OrderProduct;
import models.entities.Product;
import models.entities.Shop;
import models.exceptions.ErrorOrderNotFound;
import models.exceptions.ErrorShopNotFound;
import models.exceptions.OrderProductNotFound;
import persistence.ReadXML;
import view.user.ConstanstUIUser;
import view.user.MainWindowUser;

public class ControllerUser implements ActionListener, KeyListener, ChangeListener {

	private MainWindowUser mainWindowUser;
	private ManagerAsingProduct managerAsingProduct;
	private GeneralController generalController;
	private ManagerShop managerShop;
	private ManagerUser managerUser;
	private ManagerOrder managerOrder;
	private ManagerOrderProduct managerOrderProduct;
	private ManagerProduct managerProduct;

	public ControllerUser(GeneralController generalController, ManagerShop managerShop, ManagerAsingProduct managerAsingProduct) {
		this.generalController = generalController;
		this.managerShop = managerShop;
		managerUser = new ManagerUser();
		managerOrder = new ManagerOrder();
		managerProduct = new ManagerProduct();
		mainWindowUser = new MainWindowUser(this);
		managerOrderProduct = new ManagerOrderProduct();
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
		case ADD_PRODUCT_TO_CAR:
			try {
				addProductToCar(Integer.parseInt(((JButton)arg2.getSource()).getName()));
			} catch (OrderProductNotFound e) {
				e.printStackTrace();
			}
			mainWindowUser.showMessageDialog("Product Added To Car Successfully");
			break;
		default:
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

	/**
	 * 
	 * busca el producto por id lo agraga a la lista del carro
	 * 2@author DAYAN
	 * @throws OrderProductNotFound 
	 * el 3 es de prueba dado que no hay aun casilla de especificacion de cuantos productos se desean de uno
	 */
	private void addProductToCar(int id) throws OrderProductNotFound{
		try {			
			Product selectedProduct = managerProduct.searhProduct(id);
			managerOrderProduct.addOrderProduct(new OrderProduct(selectedProduct, 3));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ErrorOrderNotFound e) {
			throw new OrderProductNotFound();
		}
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