package controller;

/**
 * Controlador de tienda, ejecuta opciones relacionadas con producto y con tienda
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import models.dao.ManagerAsingProduct;
import models.dao.ManagerShop;
import models.entities.Product;
import models.entities.Shop;
import models.exceptions.ErrorShopNotFound;
import persistence.ReadXML;
import view.admin.AddShopDialog;
import view.admin.MainWindowAdmin;
import view.login.LoginMainWindow;
import view.shop.AddProductFromShopViewDialog;
import view.shop.MainWindowShop;

public class ControllerShop implements ActionListener, KeyListener, ChangeListener {

	private LoginMainWindow dialogoLogin;
	private ManagerShop managerShop;
	private MainWindowAdmin mainWindowAdmin;
	private MainWindowShop mainWindowShop;
	private AddShopDialog addShopDialog;
	private AddProductFromShopViewDialog addProductFromShopViewDialog;
	private ManagerAsingProduct managerAsingProduct;
	private GeneralController generalController;
	private Shop shop;

	public ControllerShop(GeneralController generalController, LoginMainWindow loginMainWindow) {
		this.generalController = generalController;
		this.dialogoLogin = loginMainWindow;
		managerShop = new ManagerShop();
		managerAsingProduct = new ManagerAsingProduct();
		mainWindowShop = new MainWindowShop(this);
		addProductFromShopViewDialog = new AddProductFromShopViewDialog(mainWindowShop, this);
	}

	public ManagerShop getManagerShop() {
		return managerShop;
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		switch (ActionsShop.valueOf(arg.getActionCommand())) {
		case ADD_PRODUCT_TO_SHOP:
			addProductToShop();
			break;
		case CHARGE_IMAGE_PRODUCT_FROM_SHOP_VIEW:
			chargeImageProductFromShopView();
			break;
		case EDIT_PRODUCT_TO_SHO:
			// TODO:
			break;
		case LOG_OUT:
			mainWindowShop.setVisible(false);
			generalController.LoginVisible();
			break;
		case SHOW_ADD_PRODUCT_FROM_SHOP_VIEW_DIALOG:
			showAddProductFromShopViewDialog();
			break;
		case ADD_IMAGE_TO_SHOP:
			addShopDialog.openFileChooser();
			break;
		case SHOW_ADD_SHOP_DIALOG:
			addShopDialog.setVisible(true);
			break;
		case EDIT_SHOP:
			editShop();
			break;
		case ADD_SHOP:
			// TODO:
			break;
		case CONFIRM:
			
			break;
		case OPEN_DIALOG_CHOOSE:
			
			break;
		case SHOP_VIEW:
			
			break;
		}
	}

	public void run() {
		dialogoLogin.setVisible(true);
	}

	private void editShop() {
		try {
			managerShop.editShop(addShopDialog.getShop(), managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			addShopDialog.setVisible(false);
			addShopDialog.changeActionToAddShop();
			ReadXML.writeShop(managerShop.getListShop());
		} catch (ParserConfigurationException | SAXException | IOException | ErrorShopNotFound
				| TransformerException e) {
			e.printStackTrace();
		}
	}

	private void showAddProductFromShopViewDialog() {
		addProductFromShopViewDialog.showToAddProduct();
		addProductFromShopViewDialog.setVisible(true);
	}

	private void chargeImageProductFromShopView() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		File file = fileChooser.getSelectedFile();
		addProductFromShopViewDialog.addImage(file.getAbsolutePath());
	}

	private void addProductToShop() {
		Product createdProduct;
		try {
			createdProduct = addProductFromShopViewDialog.getCreatedProduct();
			managerAsingProduct.addAssignmentProductShop(
					ManagerAsingProduct.createAssignmentProductShop(createdProduct, shop));
			mainWindowShop.refreshProductsTable(managerAsingProduct.getProductsListFromShop(shop));
			addProductFromShopViewDialog.setVisible(false);
			ReadXML.writeAssigmentProduct(managerAsingProduct.getAssignmentsProductsShopList());
		} catch (TransformerException | NumberFormatException | ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setThisShop(Shop shop){
		this.shop = shop;
	}

	private void refreshShopData() {
		try {
			managerAsingProduct.addAllAssignmentProductShop((ReadXML.readAsigmentProducts()));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO poner Exepciones
		}
	}
	
	public void setVisible() {
		refreshShopData();
		mainWindowShop.refreshProductsTable(managerAsingProduct.getProductsListFromShop(this.shop));
		mainWindowShop.setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}