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
import models.dao.ManagerAsingProduct;
import models.dao.ManagerOrder;
import models.dao.ManagerOrderProduct;
import models.dao.ManagerProduct;
import models.dao.ManagerShop;
import models.dao.ManagerUser;
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

public class ControllerAdmin implements ActionListener, KeyListener, ChangeListener {

	

	private ManagerAsingProduct adminManagerAssingProduct;
	private MainWindowAdmin mainWindowAdmin;
	private AddUserDialog addUserDialog;
	private AddProductDialog addProductDialog;
	private DialogChooseWhoYouAre chooseWhoYouAre;
	//private ReadXML readXML; 
	private int actualPage;
	private ManagerProduct managerProduct;
	private ManagerShop managerShop;
	private AddShopDialog addShopDialog;
	private MainWindowUser mainWindowUser;
	private DialogLogIn logIn;
	private ManagerUser manageruser;
	public ControllerAdmin() {
		
		adminManagerAssingProduct = new ManagerAsingProduct();
		mainWindowAdmin = new MainWindowAdmin(this);
//		readXML = new ReadXML();
		addProductDialog = new AddProductDialog(mainWindowAdmin, this);
		chooseWhoYouAre = new DialogChooseWhoYouAre(this);
//		try {
//          refreshDataUser(readXML.readUser()); este metodo esta en el controller de user
//			refreshDataShop(readXML.readShop());
//			refreshDataProduct(readXML.readProduct());
//			refreshDataAssigmentProductShop(readXML.readAsigmentProducts());
//		} catch (SAXException | ParserConfigurationException | IOException e) {
//			e.printStackTrace();
//		}
//		actualPage = 1;
//	    refreshList(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ActionsAdmin.valueOf(e.getActionCommand())) {
		case ADD_USER:
			addUser();
			break;
		case ADD_PRODUCT:
			addProduct();
			break;
		case CANCEL_PRODUCT:
			cancelProduct();
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
		case SHOW_EDIT_USER:
			showEditUser();
			break;
		case SHOW_EDIT_SHOP:
			showEditShop();
			break;
		case OPEN_DIALOG_CHOOSE:
			chooseWhoYouAre.setVisible(true);
			break;
		case USER_LOG_IN:
			addUserDialog.setVisible(true);
			chooseWhoYouAre.setVisible(false);
			break;
		case SHOP_LOG_IN:
			chooseWhoYouAre.setVisible(false);
			break;
		case ADMIN_VIEW:
			break;
		case EDIT_PRODUCT_TO_SHOP:
			break;
		case OPEN_DIALOG_LOG_IN:
			break;
		case LOG_OUT:
			mainWindowAdmin.setVisible(false);
			break;
		}
	}

	
	/**
	 * Este metodo fue la unica manera de que no me lanzara la excepcio asi que por favor no lo borre 
	 * o si saben como solucionarlo de una manera mejor por favor podrian hacerlo y despues explicarme
	 * att: Brayan 
	 * @return retorna la tienda encontrada anteriormente
	 */
	
	
	private void showEditShop() {
		addShopDialog.changeActionToEditShop();
		try {
			addShopDialog.setForm(managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
		addShopDialog.setVisible(true);
	}

	public Shop findTheShopHelper(){
		Shop shop = null;
		try {
			shop = managerShop.searchShopName(logIn.getTheName());
		} catch (ErrorShopNotFound e1) {
			e1.printStackTrace();
		}
		return shop;
	}

	private void showEditUser() {
		addUserDialog.changeActionToEditUser();
		try {
			addUserDialog.setForm(manageruser.searhUser(mainWindowAdmin.getIdToTableUser()));
		} catch (ErrorUserNotFound e) {
			e.printStackTrace();
		}
		addUserDialog.setVisible(true);
	}

	private void showEditProduct() {
		addProductDialog.changeActionToProductEdit();
		try {
			addProductDialog.chargeProductInWindow(managerProduct.searhProduct(mainWindowAdmin.getIdToTableProducts()));
		} catch (ErrorOrderNotFound e) {
			e.printStackTrace();
		}
		addProductDialog.setVisible(true);
	}

	private void cancelProduct() {
		addProductDialog.cleanForm();
		addProductDialog.setVisible(false);
	}

	private void editShop() {
		try {
			managerShop.editShop(addShopDialog.getShop(),managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			refreshList(0);
			addShopDialog.setVisible(false);
			addShopDialog.changeActionToAddShop();
			//readXML.writeShop(managerShop.getListShop());
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}

	private void editUser() {
		try {
			manageruser.editUser(addUserDialog.getUser(), manageruser.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			addUserDialog.setVisible(false);
			addUserDialog.changeActionToAddUser();
			//readXML.writeUser(manageruser.getUsersList());
		} catch (NumberFormatException | ErrorUserNotFound e) {
			e.printStackTrace();
		}
	}

	private void editProduct() {
		try {
		managerProduct.editProduct(addProductDialog.extractProductFromWindow(),
				managerProduct.searhProduct(mainWindowAdmin.getIdToTableProducts()));
			actualPage = 1;
			refreshList(2);
			addProductDialog.setVisible(false);
			addProductDialog.changeActionToProductAdd();
		//	readXML.writeProduct(managerProduct.getListProducts());
		} catch (NumberFormatException | ErrorOrderNotFound e) {
			e.printStackTrace();
		}
	}

	private void deleteUser() {
		try {
			manageruser.deleteUser(manageruser.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			//readXML.writeUser(manageruser.getUsersList());
		} catch (ErrorUserNotFound e) {
			e.printStackTrace();
		}
	}

	private void deleteShop() {
		try {
			managerShop.delteShop(managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			refreshList(0);
			//readXML.writeShop(managerShop.getListShop());
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}


	private void refreshDataProduct(ArrayList<Product> readProduct) {
		for (Product product : readProduct) {
			managerProduct.addProduct(product);
		}
	}
	
	private void refreshDataAssigmentProductShop(ArrayList<AssignmentProductShop> readAssignmentProductShops) {
		for (AssignmentProductShop assigment : readAssignmentProductShops) {
			adminManagerAssingProduct.addAssignmentProductShop(assigment);
		}
	}

	private void refreshDataShop(ArrayList<Shop> readShop) {
		for (Shop shop : readShop) {
			managerShop.addShop(shop);
		}
	}

	private void deleteProduct() {
		try {
			managerProduct.deleteProduct(managerProduct.searhProduct(mainWindowAdmin.getIdToTableProducts()));
			actualPage = 1;
			refreshList(2);
			//readXML.writeProduct(managerProduct.getListProducts());
		} catch (ErrorOrderNotFound e) {
			e.printStackTrace();
		}
	}

	private void addProduct() {
		//adminManagerAssingProduct.summIdProduct(); marco no se si esta linea sea necesaria cuando mejores la persistencia por eso esta comentariada
		managerProduct.addProduct(addProductDialog.extractProductFromWindow());
		cancelProduct();
		//readXML.writeProduct(managerProduct.getListProducts());
		mainWindowAdmin.showMessageDialog("Product Added Successfully");
		actualPage = 1;
		refreshList(2);
	}
	
	private void addUser() {
		manageruser.addUser(addUserDialog.getUser());
		addUserDialog.cleanForm();
		addUserDialog.setVisible(false);
		//readXML.writeUser(manageruser.getUsersList());
		mainWindowAdmin.showMessageDialog("Se ha añadido el usuario con exito");
		actualPage = 1;
		refreshList(1);
	}

	private void addShop() {
		//adminManagerAssingProduct.summIdShop();
		managerShop.addShop(addShopDialog.getShop());
		addShopDialog.cleanForm();
		addShopDialog.setVisible(false);
		//readXML.writeShop(managerShop.getListShop());
		mainWindowAdmin.showMessageDialog("Se ha añadido la tienda con exito");
		actualPage = 1;
		refreshList(0);
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
				adminManagerAssingProduct.getTotalPages(adminManagerAssingProduct.returnListDependIndex(mainWindowAdmin.getTabbedPaneIndex())));
		switch (index) {
		case 0:
			mainWindowAdmin.refreshTableShop(
					(ArrayList<Shop>) adminManagerAssingProduct.paginate(adminManagerAssingProduct.returnListDependIndex(index), actualPage));
			break;
		case 1:
			mainWindowAdmin.refreshTableUser(
					(ArrayList<User>) adminManagerAssingProduct.paginate(adminManagerAssingProduct.returnListDependIndex(index), actualPage));
			break;
		case 2:
			mainWindowAdmin.refreshTableProducts(
					(ArrayList<Product>) adminManagerAssingProduct.paginate(adminManagerAssingProduct.returnListDependIndex(index), actualPage));
			break;

		default:
			break;
		}
	}

	private void changeToNextPage() {
		if (actualPage < adminManagerAssingProduct
				.getTotalPages(adminManagerAssingProduct.returnListDependIndex(mainWindowAdmin.getTabbedPaneIndex()))) {
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