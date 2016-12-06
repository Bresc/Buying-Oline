package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import models.dao.ManagerAsingProduct;
import models.dao.ManagerShop;
import models.entities.Product;
import models.entities.Shop;
import models.exceptions.ErrorShopNotFound;
import view.admin.AddShopDialog;
import view.admin.MainWindowAdmin;
import view.login.DialogLogIn;
import view.shop.AddProductFromShopViewDialog;
import view.shop.MainWindowShop;

public class ControllerShop  implements ActionListener, KeyListener, ChangeListener {
	
	private DialogLogIn dialogoLogin;
	private ManagerShop managerShop;
	private MainWindowAdmin mainWindowAdmin;
	private MainWindowShop mainWindowShop;
	private AddShopDialog addShopDialog;
	private AddProductFromShopViewDialog addProductFromShopViewDialog;
	private ManagerAsingProduct managerAsingProduct;
	private int actualPage;
	
	public ControllerShop() {
		mainWindowAdmin = new MainWindowAdmin(new ControllerAdmin());
		actualPage = 1;
		dialogoLogin = new DialogLogIn(this);
		managerShop = new ManagerShop();
		mainWindowShop = new MainWindowShop(this);
		addShopDialog = new  AddShopDialog(	mainWindowShop, this);
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
			
			break;
		case LOG_OUT:
			
			break;
		case SHOW_ADD_PRODUCT_FROM_SHOP_VIEW_DIALOG:
			showAddProductFromShopViewDialog();
			break;
		case ADD_IMAGE_TO_SHOP:
			addShopDialog.openFileChooser();
			break;
		case ADD_SHOP:
			
			break;
		case SHOP_VIEW:
			
			break;
		case SHOW_ADD_SHOP_DIALOG:
			addShopDialog.setVisible(true);
			break;
		case EDIT_SHOP:
			editShop();
			break;
		default:
			break;
		}
	}
	
	private void editShop() {
		try {
			managerShop.editShop(addShopDialog.getShop(), managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			//refreshList(0);
			addShopDialog.setVisible(false);
			addShopDialog.changeActionToAddShop();
			//readXML.writeShop(adminManagerAssingProduct.getListShop());
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}
	
	private void showAddProductFromShopViewDialog() {
		addProductFromShopViewDialog.showToAddProduct();
		addProductFromShopViewDialog.setVisible(true);
	}
	
	private void chargeImageProductFromShopView(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		File file = fileChooser.getSelectedFile();
		addProductFromShopViewDialog.addImage(file.getAbsolutePath());
	}
	
	private void addProductToShop() {
		Product createdProduct = addProductFromShopViewDialog.getCreatedProduct();
		managerAsingProduct.addAssignmentProductShop(ManagerAsingProduct.createAssignmentProductShop(createdProduct,
				getActualShop()));
		mainWindowShop.refreshProductsTable(managerAsingProduct.getProductsListFromShop(getActualShop()));
		addProductFromShopViewDialog.setVisible(false);
	}
	
	private Shop getActualShop() {
		return findTheShopHelper();
	}

	public Shop findTheShopHelper(){
		Shop shop = null;
		try {
			shop = managerShop.searchShopName(dialogoLogin.getTheName());
		} catch (ErrorShopNotFound e1) {
			e1.printStackTrace();
		}
		return shop;
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
