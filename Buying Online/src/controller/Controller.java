package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.admin.AddShopDialog;
import view.admin.MainWindowAdmin;
import view.user.MainWindowUser;
import wiew.product.PanelProduct;

public class Controller implements ActionListener {

	private MainWindowUser mainWindowUser;
	private MainWindowAdmin mainWindowAdmin;
	
	private AddShopDialog addShopDialog;

	public Controller() {
		mainWindowAdmin = new MainWindowAdmin(this);
		mainWindowUser = new MainWindowUser(this);
		
		addShopDialog = new AddShopDialog(mainWindowAdmin, this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ADD_USER:
			break;
		case ADD_PRODUCT:
			break;
		case CANCEL_PRODUCT:
			break;
		case CHARGE_IMAGE:
			break;
		case SEARCH_RESTAURANT:
			break;
		case SHOW_DROP_DOWN_MENU:
			break;
		case ADD_IMAGE:
			addShopDialog.openFileChooser();
			break;
		case ADD_SHOP:
			addShop();
			break;
		case SHOW_ADD_SHOP_DIALOG:
			addShopDialog.setVisible(true);
			break;
		}
	}

	private void addShop() {
		// TODO Auto-generated method stub
	}
}