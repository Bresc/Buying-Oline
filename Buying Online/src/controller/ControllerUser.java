package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.xml.sax.SAXException;

import models.dao.ManagerAsingProduct;
import models.dao.ManagerShop;
import models.dao.ManagerUser;
import models.entities.Shop;
import models.entities.User;
import models.exceptions.ErrorShopNotFound;
import view.admin.AddUserDialog;
import view.user.MainWindowUser;

public class ControllerUser implements ActionListener, KeyListener, ChangeListener{
	
	private MainWindowUser mainWindowUser;
	private ManagerUser managerUser;
	private AddUserDialog addUserDialog;
	private ManagerShop managerShop;
	private ManagerAsingProduct adminManagerAssingProduct;
	private User user;
	public ControllerUser() {
		mainWindowUser = new MainWindowUser(this);
		addUserDialog = new AddUserDialog(mainWindowUser , this);
	}

	

	@Override
	public void actionPerformed(ActionEvent arg2) {
		switch (ActionsUser.valueOf(arg2.getActionCommand())) {
		case BACK_TO_PANEL_RESTAURANTS:
			mainWindowUser.refreshShopList(managerShop.getListShop(), this);
			break;
		case CANCEL_PRODUCT:
			
			break;
		case CHANGE_TO_PRODUCTS_FROM_SHOP_PANEL:
			
			break;
		case CONFIRM:
			
			break;
		case LOG_OUT:
			
			break;
		case OPT_USER_VIEW_PRODUCTS:
			
			break;
		case SEARCH_RESTAURANT:
			
			break;
		case SHOPPING_CAR_USER:
			
			break;
		case SHOW_EDIT_USER:
			
			break;
		case SHOW_PRODUCTS_BY_SHOP:
			
			showProductsByShop(((JButton) arg2.getSource()).getName());
			break;
		case USER_SETTINGS:
			
			break;
		case USER_VIEW:
			
			break;
		case VIEW_USER_FAVORITES:
			
			break;
		case ADD_IMAGE_TO_USER:
			
			break;
		case ADD_USER:
			
			break;
		case EDIT_USER:
			
			break;
		default:
			break;
		
		}
		
	}
	
	private void showProductsByShop(String id) {
		try {
			mainWindowUser.changeToProductsFromShopPanel(
					adminManagerAssingProduct.getProductsListFromShop(managerShop.searhShop(Integer.parseInt(id))));
		} catch (NumberFormatException | ErrorShopNotFound e) {
			e.printStackTrace();
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

	@SuppressWarnings("unused")
	private void refreshDataUser(ArrayList<User> users) throws SAXException {
		for (User user : users) {
			managerUser.addUser(user);
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



	public void setVi() {
		mainWindowUser.setVi();
	}

}
