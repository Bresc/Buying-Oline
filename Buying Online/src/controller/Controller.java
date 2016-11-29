package controller;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import models.dao.AdminManager;
import view.admin.AddShopDialog;
import view.admin.AddUserDialog;
import view.admin.MainWindowAdmin;
import view.user.MainWindowUser;
import wiew.product.WindowAddProduct;

public class Controller implements ActionListener {

	private MainWindowUser mainWindowUser;
	private MainWindowAdmin mainWindowAdmin;
	private AddShopDialog addShopDialog;
	private AdminManager adminManager;
	private AddUserDialog addUserDialog;
	private WindowAddProduct addProductDialog;

	public Controller() {
		mainWindowAdmin = new MainWindowAdmin(this);
		mainWindowUser = new MainWindowUser(this);
		adminManager = new AdminManager();
		addShopDialog = new AddShopDialog(mainWindowAdmin, this);
		addUserDialog = new AddUserDialog(mainWindowAdmin, this);
		addProductDialog = new WindowAddProduct(mainWindowAdmin, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ADD_USER:
			addUser();
			break;
		case ADD_PRODUCT:
			addProduct();
			break;
		case CANCEL_PRODUCT:
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
		default:
			break;
		}
	}

	private void addProduct() {
		adminManager.addProduct(addProductDialog.extractProductFromWindow());
		mainWindowAdmin.refreshTableProducts(adminManager.getListProducts());
		addProductDialog.cleanForm();
		addProductDialog.setVisible(false);
		mainWindowAdmin.showMessageDialog("Product Added Successfully");
	}

	private void addUser() {
		adminManager.addUser(addUserDialog.getUser());
		mainWindowAdmin.refreshTableUser(adminManager.getUsersLsit());
		addUserDialog.cleanForm();
		addUserDialog.setVisible(false);
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