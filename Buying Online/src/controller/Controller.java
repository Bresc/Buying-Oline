package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import models.dao.AdminManager;
import models.entities.AssignmentProductShop;
import models.entities.Product;
import models.entities.Shop;
import models.entities.User;
import models.exceptions.ErrorOrderNotFound;
import models.exceptions.ErrorShopNotFound;
import models.exceptions.ErrorUserNotFound;
import persistence.ReadXML;
import view.admin.AddProductDialog;
import view.admin.AddShopDialog;
import view.admin.AddUserDialog;
import view.admin.MainWindowAdmin;
import view.login.DialogChooseWhoYouAre;
import view.login.DialogLogIn;
import view.shop.AddProductFromShopViewDialog;
import view.shop.MainWindowShop;
import view.user.MainWindowUser;

public class Controller implements ActionListener, KeyListener, ChangeListener {

	private MainWindowUser mainWindowUser;
	private MainWindowAdmin mainWindowAdmin;
	private MainWindowShop mainWindowShop;
	private AddShopDialog addShopDialog;
	private AdminManager adminManager;
	private AddUserDialog addUserDialog;
	private AddProductDialog addProductDialog;
	private DialogChooseWhoYouAre chooseWhoYouAre;
	private DialogLogIn logIn;
	private AddProductFromShopViewDialog addProductFromShopViewDialog;
	private ReadXML readXML;
	private int actualPage;

	public Controller() {
		adminManager = new AdminManager();
		mainWindowAdmin = new MainWindowAdmin(this);
		mainWindowUser = new MainWindowUser(this);
		mainWindowShop = new MainWindowShop();
		readXML = new ReadXML();
		addProductFromShopViewDialog = new AddProductFromShopViewDialog(mainWindowAdmin, this);
		addShopDialog = new AddShopDialog(mainWindowAdmin, this);
		addUserDialog = new AddUserDialog(mainWindowAdmin, this);
		addProductDialog = new AddProductDialog(mainWindowAdmin, this);
		chooseWhoYouAre = new DialogChooseWhoYouAre(this);
		logIn = new DialogLogIn(this);
		try {
			refreshDataUser(readXML.readUser());
			refreshDataShop(readXML.readShop());
			refreshDataProduct(readXML.readProduct());
			refreshDataAssigmentProductShop(readXML.readAsigmentProducts());
		} catch (SAXException | ParserConfigurationException | IOException e) {
			e.printStackTrace();
		}
		actualPage = 1;
		refreshList(0);
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
			cancelProduct();
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
		case CHARGE_IMAGE_PRODUCT_FROM_SHOP_VIEW:
			chargeImageProductFromShopView();
		case ADD_SHOP:
			addShop();
			break;
		case ADD_PRODUCT_TO_SHOP:
			addProductToShop();
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
			changeToPreviousPage();
			break;
		case GO_RIGHT_ARROW:
			changeToNextPage();
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
		case SHOW_EDIT_PRODUCT:
			showEditProduct();
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
			break;
		case SHOW_ADD_PRODUCT_FROM_SHOP_VIEW_DIALOG:
			showAddProductFromShopViewDialog();
			break;
		case SHOW_EDIT_USER:
			showEditUser();
			break;
		case SHOW_PRODUCTS_BY_SHOP:
			showProductsByShop(((JButton) e.getSource()).getName());
			break;
		case SHOW_EDIT_SHOP:
			showEditShop();
			break;
		case CONFIRM:
			cofirm();
			break;
		case OPEN_DIALOG_CHOOSE:
			logIn.setVisible(false);
			chooseWhoYouAre.setVisible(true);
			break;
		case USER_LOG_IN:
			addUserDialog.setVisible(true);
			chooseWhoYouAre.setVisible(false);
			break;
		case SHOP_LOG_IN:
			addShopDialog.setVisible(true);
			chooseWhoYouAre.setVisible(false);
			break;
		case ADMIN_VIEW:
			break;
		case EDIT_PRODUCT_TO_SHOP:
			break;
		case OPEN_DIALOG_LOG_IN:
			break;
		case OPT_USER_VIEW_PRODUCTS:
			break;
		case SHOPPING_CAR_USER:
			break;
		case SHOP_VIEW:
			break;
		case USER_SETTINGS:
			break;
		case USER_VIEW:
			break;
		case VIEW_USER_FAVORITES:
			break;
		}
	}

	private void showAddProductFromShopViewDialog() {
		addProductFromShopViewDialog.showToAddProduct();
		addProductFromShopViewDialog.setVisible(true);
	}

	private void cofirm() {
		if (adminManager.searchForLogIn(logIn.getTheName(), logIn.getPassword()).equals("user")) {
			try {
				adminManager.searchUserNamePassword(logIn.getTheName(), logIn.getPassword());
				mainWindowUser.refreshShopList(adminManager.getListShop(), this);
				mainWindowUser.setVisible(true);
				logIn.setVisible(false);
			} catch (ErrorUserNotFound e) {
				e.printStackTrace();
			}
		}else if (adminManager.searchForLogIn(logIn.getTheName(), logIn.getPassword()).equals("shop")) {
				Shop shop = findTheShopHelper();
				mainWindowShop.refreshProductsTable(adminManager.getProductsListFromShop(shop));
				mainWindowShop.setVisible(true);
				logIn.setVisible(false);
		}else{
			mainWindowAdmin.setVisible(true);
			logIn.setVisible(false);
		}
	}
	
	/**
	 * Este metodo fue la unica manera de que no me lanzara la excepcio asi que por favor no lo borre 
	 * o si saben como solucionarlo de una manera mejor por favor podrian hacerlo y despues explicarme
	 * att: Brayan 
	 * @return retorna la tienda encontrada anteriormente
	 */
	public Shop findTheShopHelper(){
		Shop shop = null;
		try {
			shop = adminManager.searchShopName(logIn.getTheName());
		} catch (ErrorShopNotFound e1) {
			e1.printStackTrace();
		}
		return shop;
	}
	
	private void showEditShop() {
		addShopDialog.changeActionToEditShop();
		try {
			addShopDialog.setForm(adminManager.searhShop(mainWindowAdmin.getIdToTableShops()));
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
		addShopDialog.setVisible(true);
	}

	private void showProductsByShop(String id) {
		try {
			mainWindowUser.changeToProductsFromShopPanel(
					adminManager.getProductsListFromShop(adminManager.searhShop(Integer.parseInt(id))));
		} catch (NumberFormatException | ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}
	
	private void showEditUser() {
		addUserDialog.changeActionToEditUser();
		try {
			addUserDialog.setForm(adminManager.searhUser(mainWindowAdmin.getIdToTableUser()));
		} catch (ErrorUserNotFound e) {
			e.printStackTrace();
		}
		addUserDialog.setVisible(true);
	}

	private void showEditProduct() {
		addProductDialog.changeActionToProductEdit();
		try {
			addProductDialog.chargeProductInWindow(adminManager.searhProduct(mainWindowAdmin.getIdToTableProducts()));
		} catch (ErrorOrderNotFound e) {
			e.printStackTrace();
		}
		addProductDialog.setVisible(true);
	}

	private void cancelProduct() {
		addProductDialog.cleanForm();
		addProductDialog.setVisible(false);
	}


	public void changeToProductsFromShopPanel(ActionEvent e) {
		JButton bntSource = (JButton) (e.getSource());
		try {
			Shop selectedShop = adminManager.searhShop(Integer.parseInt(bntSource.getName()));
			mainWindowUser.changeToProductsFromShopPanel(adminManager.getProductsListFromShop(selectedShop));
		} catch (NumberFormatException | ErrorShopNotFound e1) {
			e1.printStackTrace();
		}
	}

	private void editShop() {
		try {
			adminManager.editShop(addShopDialog.getShop(), adminManager.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			refreshList(0);
			addShopDialog.setVisible(false);
			addShopDialog.changeActionToAddShop();
			readXML.writeShop(adminManager.getListShop());
		} catch (ErrorShopNotFound | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void editUser() {
		try {
			adminManager.editUser(addUserDialog.getUser(), adminManager.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			addUserDialog.setVisible(false);
			addUserDialog.changeActionToAddUser();
			readXML.writeUser(adminManager.getUsersList());
		} catch (NumberFormatException | ErrorUserNotFound | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void editProduct() {
		try {
			adminManager.editProduct(addProductDialog.extractProductFromWindow(),
					adminManager.searhProduct(mainWindowAdmin.getIdToTableProducts()));
			actualPage = 1;
			refreshList(2);
			addProductDialog.setVisible(false);
			addProductDialog.changeActionToProductAdd();
			readXML.writeProduct(adminManager.getListProducts());
		} catch (NumberFormatException | ErrorOrderNotFound | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void deleteUser() {
		try {
			adminManager.deleteUser(adminManager.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			readXML.writeUser(adminManager.getUsersList());
		} catch (TransformerException | ParserConfigurationException | ErrorUserNotFound e) {
			e.printStackTrace();
		}
	}

	private void deleteShop() {
		try {
			adminManager.delteShop(adminManager.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			refreshList(0);
			readXML.writeShop(adminManager.getListShop());
		} catch (ErrorShopNotFound | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void refreshDataUser(ArrayList<User> users) throws SAXException {
		for (User user : users) {
			adminManager.addUser(user);
		}
	}

	private void refreshDataProduct(ArrayList<Product> readProduct) {
		for (Product product : readProduct) {
			adminManager.addProduct(product);
		}
	}
	
	private void refreshDataAssigmentProductShop(ArrayList<AssignmentProductShop> readAssignmentProductShops) {
		for (AssignmentProductShop assigment : readAssignmentProductShops) {
			adminManager.addAssignmentProductShop(assigment);
		}
	}

	private void refreshDataShop(ArrayList<Shop> readShop) {
		for (Shop shop : readShop) {
			adminManager.addShop(shop);
		}
	}

	private void deleteProduct() {
		try {
			adminManager.deleteProduct(adminManager.searhProduct(mainWindowAdmin.getIdToTableProducts()));
			actualPage = 1;
			refreshList(2);
			readXML.writeProduct(adminManager.getListProducts());
		} catch (ErrorOrderNotFound | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void addProduct() {
		try {
			adminManager.addProduct(addProductDialog.extractProductFromWindow());
			cancelProduct();
			readXML.writeProduct(adminManager.getListProducts());
			mainWindowAdmin.showMessageDialog("Product Added Successfully");
			actualPage = 1;
			refreshList(2);
		} catch (TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	private void addProductToShop() {
//		Product createdProduct = addProductFromShopViewDialog.getCreatedProduct();
//		adminManager.addAssignmentProductShop(AdminManager.createAssignmentProductShop(createdProduct,
//				mainWindowShop.getActualShop()));
//		mainWindowShop.refreshProductsTable(adminManager.getProductsListFromShop(mainWindowShop.getActualShop()));
		mainWindowAdmin.showMessageDialog("Product Added Successfully");
		addProductFromShopViewDialog.setVisible(false);
	}

	private void addUser() {
		try {
			adminManager.addUser(addUserDialog.getUser());
			addUserDialog.cleanForm();
			addUserDialog.setVisible(false);
			readXML.writeUser(adminManager.getUsersList());
			mainWindowAdmin.showMessageDialog("Se ha añadido el usuario con exito");
			actualPage = 1;
			refreshList(1);			
		} catch (TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void addShop() {
		try {
			adminManager.addShop(addShopDialog.getShop());
			addShopDialog.cleanForm();
			addShopDialog.setVisible(false);
			readXML.writeShop(adminManager.getListShop());
			mainWindowAdmin.showMessageDialog("Se ha añadido la tienda con exito");
			actualPage = 1;
			refreshList(0);
		} catch (TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		logIn.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// logIn.changeTheButtonUser(adminManager.searchForLogInUser(logIn.getName(),
		// logIn.getPassword()));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// logIn.changeTheButtonUser(adminManager.searchForLogInUser(logIn.getName(),
		// logIn.getPassword()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// logIn.changeTheButtonUser(adminManager.searchForLogInUser(logIn.getName(),
		// logIn.getPassword()));
	}

	// paginacion
	@Override
	public void stateChanged(ChangeEvent e) {
		JTabbedPane tp = (JTabbedPane) e.getSource();
		actualPage = 1;
		refreshList(tp.getSelectedIndex());
	}

	@SuppressWarnings("unchecked")
	public void refreshList(int index) {
		mainWindowAdmin.refreshPage(actualPage,
				adminManager.getTotalPages(adminManager.returnListDependIndex(mainWindowAdmin.getTabbedPaneIndex())));
		switch (index) {
		case 0:
			mainWindowAdmin.refreshTableShop(
					(ArrayList<Shop>) adminManager.paginate(adminManager.returnListDependIndex(index), actualPage));
			break;
		case 1:
			mainWindowAdmin.refreshTableUser(
					(ArrayList<User>) adminManager.paginate(adminManager.returnListDependIndex(index), actualPage));
			break;
		case 2:
			mainWindowAdmin.refreshTableProducts(
					(ArrayList<Product>) adminManager.paginate(adminManager.returnListDependIndex(index), actualPage));
			break;

		default:
			break;
		}
	}

	private void chargeImageProductFromShopView(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		File file = fileChooser.getSelectedFile();
		addProductFromShopViewDialog.addImage(file.getAbsolutePath());
	}
	
	private void changeToNextPage() {
		if (actualPage < adminManager
				.getTotalPages(adminManager.returnListDependIndex(mainWindowAdmin.getTabbedPaneIndex()))) {
			actualPage++;
			refreshList(mainWindowAdmin.getTabbedPaneIndex());
		}
	}

	private void changeToPreviousPage() {
		if (actualPage > 1) {
			actualPage--;
			refreshList(mainWindowAdmin.getTabbedPaneIndex());
		}
	}
}