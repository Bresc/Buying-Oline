package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import models.dao.AdminManager;
import models.entities.Product;
import models.entities.Shop;
import models.entities.User;
import models.exceptions.ErrorOrderNotFound;
import models.exceptions.ErrorShopNotFound;
import persistence.ReadXML;
import view.LogIn.DialogChooseWhoYouAre;
import view.LogIn.DialogLogIn;
import view.admin.AddProductDialog;
import view.admin.AddShopDialog;
import view.admin.AddUserDialog;
import view.admin.MainWindowAdmin;
import view.shop.MainWindowShop;
import view.user.MainWindowUser;

public class Controller implements ActionListener, KeyListener {

	private MainWindowUser mainWindowUser;
	private MainWindowAdmin mainWindowAdmin;
	private MainWindowShop mainWindowShop;
	private AddShopDialog addShopDialog;
	private AdminManager adminManager;
	private AddUserDialog addUserDialog;
	private AddProductDialog addProductDialog;
	private DialogChooseWhoYouAre chooseWhoYouAre;
	private DialogLogIn logIn;
	private ReadXML readXML;


	public Controller() {
		mainWindowAdmin = new MainWindowAdmin(this);
		mainWindowUser = new MainWindowUser(this);
		mainWindowShop = new MainWindowShop();
		adminManager = new AdminManager();
		readXML = new ReadXML();
		addShopDialog = new AddShopDialog(mainWindowAdmin, this);
		addUserDialog = new AddUserDialog(mainWindowAdmin, this);
		addProductDialog = new AddProductDialog(mainWindowAdmin, this);
		chooseWhoYouAre = new DialogChooseWhoYouAre(this);
		logIn = new DialogLogIn(this);
		try {
			refreshDataUser(readXML.readUser());
			refreshDataShop(readXML.readShop());
			refreshDataProduct(readXML.readProduct());
		} catch (SAXException | ParserConfigurationException | IOException e) {
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
				e1.printStackTrace();
			}
			break;
		case ADD_PRODUCT:
			try {
				addProduct();
			} catch (TransformerException | ParserConfigurationException e2) {
				e2.printStackTrace();
			}
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
			try {
				addShop();
			} catch (TransformerException | ParserConfigurationException e1) {
				e1.printStackTrace();
			}
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
			break;
		case OPEN_DIALOG_LOG_IN:
			OpenDialogLogIn();
			break;
		case SHOP_LOG_IN:
			shopLogIn();
			break;
		case USER_LOG_IN:
			userLogIn();
			break;
		case ADMIN_VIEW:
			adminView();
			break;
		case SHOP_VIEW:
			shopView();
			break;
		case USER_VIEW:
			userView();
			break;
		default:
			break;
		}
	}

	private void userView() {
		mainWindowUser.setVisible(true);
		logIn.setVisible(false);
	}

	private void shopView() {
		mainWindowShop.setVisible(true);
		logIn.setVisible(false);
	}

	private void adminView() {
		mainWindowAdmin.setVisible(true);
		logIn.setVisible(false);
	}
	
	private void userLogIn() {
		logIn.UserView();
		chooseWhoYouAre.setVisible(false);
	}
	
/**
 * este metodo es lo de shop
 */
	private void shopLogIn() {
		logIn.CompanyView();
		chooseWhoYouAre.setVisible(false);
	}

	private void OpenDialogLogIn() {
		logIn.adminView();
		chooseWhoYouAre.setVisible(false);
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
			readXML.writeShop(adminManager.getListShop());
		} catch (ErrorShopNotFound | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void refreshDataUser(ArrayList<User> users) throws SAXException {
		for (User user : users) {
			adminManager.addUser(user);
		}
		mainWindowAdmin.refreshTableUser(adminManager.getUsersList());
	}
	
	private void refreshDataProduct(ArrayList<Product> readProduct) {
		for (Product product : readProduct) {
			adminManager.addProduct(product);
		}
		mainWindowAdmin.refreshTableProducts(adminManager.getListProducts());
	}

	private void refreshDataShop(ArrayList<Shop> readShop) {
		for (Shop shop : readShop) {
			adminManager.addShop(shop);
		}
		mainWindowAdmin.refreshTableShop(adminManager.getListShop());
	}

	private void deleteProduct() {
		try {
			adminManager.deleteProduct(adminManager.searhProduct(mainWindowAdmin.getIdToTableProducts()));
			mainWindowAdmin.refreshTableProducts(adminManager.getListProducts());
			readXML.writeProduct(adminManager.getListProducts());
		} catch (ErrorOrderNotFound | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void addProduct() throws TransformerException, ParserConfigurationException {
		adminManager.addProduct(addProductDialog.extractProductFromWindow());
		mainWindowAdmin.refreshTableProducts(adminManager.getListProducts());
		addProductDialog.cleanForm();
		addProductDialog.setVisible(false);
		readXML.writeProduct(adminManager.getListProducts());
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

	private void addShop() throws TransformerException, ParserConfigurationException {
		adminManager.addShop(addShopDialog.getShop());
		mainWindowAdmin.refreshTableShop(adminManager.getListShop());
		addShopDialog.cleanForm();
		addShopDialog.setVisible(false);
		readXML.writeShop(adminManager.getListShop());
		mainWindowAdmin.showMessageDialog("Se ha añadido la tienda con exito");
	}



	public void run() {
		chooseWhoYouAre.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		logIn.changeTheButtonUser(adminManager.searchForLogInUser(logIn.getName(), logIn.getPassword()));
	}

	@Override
	public void keyPressed(KeyEvent e) {
		logIn.changeTheButtonUser(adminManager.searchForLogInUser(logIn.getName(), logIn.getPassword()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		logIn.changeTheButtonUser(adminManager.searchForLogInUser(logIn.getName(), logIn.getPassword()));
	}
}