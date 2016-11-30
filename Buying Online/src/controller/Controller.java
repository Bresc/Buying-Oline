package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;
import models.dao.AdminManager;
import models.entities.Shop;
import models.entities.User;
import models.exceptions.ErrorOrderNotFound;
import models.exceptions.ErrorShopNotFound;
import persistence.ReadXML;
import view.admin.AddProductDialog;
import view.admin.AddShopDialog;
import view.admin.AddUserDialog;
import view.admin.MainWindowAdmin;
import view.product.WindowAddProduct;
import view.user.MainWindowUser;

public class Controller implements ActionListener {

	private MainWindowUser mainWindowUser;
	private MainWindowAdmin mainWindowAdmin;
	private AddShopDialog addShopDialog;
	private AdminManager adminManager;
	private AddUserDialog addUserDialog;
	private AddProductDialog addProductDialog;
	private ReadXML readXML;


	public Controller() {
		mainWindowAdmin = new MainWindowAdmin(this);
		mainWindowUser = new MainWindowUser(this);
		adminManager = new AdminManager();
		readXML = new ReadXML();
		addShopDialog = new AddShopDialog(mainWindowAdmin, this);
		addUserDialog = new AddUserDialog(mainWindowAdmin, this);
		addProductDialog = new AddProductDialog(mainWindowAdmin, this);

		try {
			refreshData(readXML.readUser());
		} catch (SAXException | ParserConfigurationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ADD_USER:
			try {
				addUser();
			} catch (TransformerException | ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case ADD_PRODUCT:
			addProduct();
			break;
		case CANCEL_PRODUCT:
			addProductDialog.cleanForm();
			addProductDialog.setVisible(false);
			break;
		case SEARCH_RESTAURANT:
			break;
		case SHOW_DROP_DOWN_MENU:
			break;
		case ADD_IMAGE_TO_SHOP:
			addShopDialog.openFileChooser();
			break;
		case ADD_IMAGE_TO_USER:
			addUserDialog.openFileChooser();
			break;
		case CHARGE_IMAGE_PRODUCT:
			addProductDialog.searchForImage();
			break;
		case ADD_SHOP:
			addShop();
			break;
		case SHOW_ADD_SHOP_DIALOG:
			addShopDialog.setVisible(true);
			break;
		case SHOW_ADD_USER_DIALOG:
			addUserDialog.setVisible(true);
			break;
		case SHOW_ADD_PRODUCT:
			addProductDialog.setVisible(true);
			break;
		case CHARGE_IMAGE:
			break;
		case GO_LEFT_ARROW:
			break;
		case GO_RIGHT_ARROW:
			break;
		case DELETE_PRODUCT:
			deleteProduct();
			break;
		case DELETE_SHOP:
			deleteShop();
			break;
		case DELETE_USER:
			deleteUser();
			break;
		case EDIT_PRODUCT:
			editProduct();
			break;
		case EDIT_SHOP:
			editShop();
			break;
		case EDIT_USER:
			editUser();
			break;
		case CHANGE_TO_PRODUCTS_FROM_SHOP_PANEL:
			changeToProductsFromShopPanel(e);
		}
	}

	public void changeToProductsFromShopPanel(ActionEvent e) {
		JButton bntSource = (JButton)(e.getSource());
		try {
			Shop selectedShop = adminManager.searhShop(Integer.parseInt(bntSource.getName()));
			mainWindowUser.changeToProductsFromShopPanel(adminManager.getProductsListFromShop(selectedShop));
		} catch (NumberFormatException | ErrorShopNotFound e1) {
			e1.printStackTrace();
		}
	}

	private void editShop() {
		// TODO Auto-generated method stub

	}

	private void editUser() {
		// TODO Auto-generated method stub

	}

	private void editProduct() {
		// TODO Auto-generated method stub

	}

	private void deleteUser() {
		// TODO Auto-generated method stub

	}

	private void deleteShop() {
		try {
			adminManager.delteShop(adminManager.searhShop(mainWindowAdmin.getIdToTableShops()));
			mainWindowAdmin.refreshTableShop(adminManager.getListShop());
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}

	private void refreshData(ArrayList<User> users) throws SAXException {
		for (User user : users) {
			adminManager.addUser(user);
		}
		mainWindowAdmin.refreshTableUser(adminManager.getUsersList());
	}

	private void deleteProduct() {
		try {
			adminManager.deleteProduct(adminManager.searhProduct(mainWindowAdmin.getIdToTableProducts()));
			mainWindowAdmin.refreshTableProducts(adminManager.getListProducts());
		} catch (ErrorOrderNotFound e) {
			e.printStackTrace();
		}
	}

	private void addProduct() {
		adminManager.addProduct(addProductDialog.extractProductFromWindow());
		mainWindowAdmin.refreshTableProducts(adminManager.getListProducts());
		addProductDialog.cleanForm();
		addProductDialog.setVisible(false);
		mainWindowAdmin.showMessageDialog("Product Added Successfully");
	}

	private void addUser() throws TransformerException, ParserConfigurationException {
		adminManager.addUser(addUserDialog.getUser());
		mainWindowAdmin.refreshTableUser(adminManager.getUsersList());
		addUserDialog.cleanForm();
		addUserDialog.setVisible(false);
		readXML.writeUser(adminManager.getUsersList());
		mainWindowAdmin.showMessageDialog("Se ha añadido el usuario con exito");
	}

	private void addShop() {
		adminManager.addShop(addShopDialog.getShop());
		mainWindowAdmin.refreshTableShop(adminManager.getListShop());
		addShopDialog.cleanForm();
		addShopDialog.setVisible(false);
		mainWindowAdmin.showMessageDialog("Se ha añadido la tienda con exito");
	}
}