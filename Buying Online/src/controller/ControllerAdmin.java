package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import models.dao.ManagerAsingProduct;
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

public class ControllerAdmin implements ActionListener, KeyListener, ChangeListener {

	private ManagerAsingProduct adminManagerAssingProduct;
	private MainWindowAdmin mainWindowAdmin;
	private AddUserDialog addUserDialog;
	private AddProductDialog addProductDialog;
	private DialogChooseWhoYouAre chooseWhoYouAre;
	private int actualPage;
	private ManagerProduct managerProduct;
	private ManagerShop managerShop;
	private AddShopDialog addShopDialog;
	private DialogLogIn logIn;
	private ManagerUser manageruser;
	
	public ControllerAdmin() throws ParserConfigurationException, SAXException, IOException {
		
		manageruser = new ManagerUser();
		managerShop = new ManagerShop();
		managerProduct = new ManagerProduct();
		adminManagerAssingProduct = new ManagerAsingProduct();
		mainWindowAdmin = new MainWindowAdmin(this);
		addProductDialog = new AddProductDialog(mainWindowAdmin, this);
		chooseWhoYouAre = new DialogChooseWhoYouAre(this);
			
		actualPage = 1;
	    refreshList(0);

	    refrehAllData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ActionsAdmin.valueOf(e.getActionCommand())) {
		case ADD_USER:
			try {
				addUser();
			} catch (TransformerException | ParserConfigurationException e6) {
				// TODO Auto-generated catch block
				e6.printStackTrace();
			}
			break;
		case ADD_PRODUCT:
			try {
				addProduct();
			} catch (NumberFormatException | ParserConfigurationException | SAXException | IOException
					| TransformerException e5) {
				// TODO Auto-generated catch block
				e5.printStackTrace();
			}
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
			try {
				addShop();
			} catch (TransformerException | ParserConfigurationException | SAXException | IOException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			
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
			try {
				deleteShop();
			} catch (TransformerException | ParserConfigurationException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
			break;
		case DELETE_USER:
			try {
				deleteUser();
			} catch (TransformerException | ParserConfigurationException e2) {
				
				e2.printStackTrace();
			}
			break;
		case SHOW_EDIT_PRODUCT:
			showEditProduct();
			break;
		case EDIT_PRODUCT:
			try {
				editProduct();
			} catch (TransformerException | ParserConfigurationException | SAXException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case EDIT_SHOP:
			try {
				editShop();
			} catch (TransformerException | ParserConfigurationException | SAXException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case EDIT_USER:
			try {
				editUser();
			} catch (TransformerException | ParserConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		case ADD_IMAGE_TO_SHOP:
			break;
		case CONFIRM:
			break;
		case SHOW_ADD_SHOP_DIALOG:
			break;
		case SHOW_DROP_DOWN_MENU:
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

	private void editShop() throws TransformerException, ParserConfigurationException, SAXException, IOException {
		try {
			managerShop.editShop(addShopDialog.getShop(),managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			refreshList(0);
			addShopDialog.setVisible(false);
			addShopDialog.changeActionToAddShop();
			ReadXML.writeShop(managerShop.getListShop());
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}

	private void editUser() throws TransformerException, ParserConfigurationException {
		try {
			manageruser.editUser(addUserDialog.getUser(), manageruser.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			addUserDialog.setVisible(false);
			addUserDialog.changeActionToAddUser();
			ReadXML.writeUser(manageruser.getUsersList());
		} catch (NumberFormatException | ErrorUserNotFound e) {
			e.printStackTrace();
		}
	}

	private void editProduct() throws TransformerException, ParserConfigurationException, SAXException, IOException {
		try {
		managerProduct.editProduct(addProductDialog.extractProductFromWindow(),
				managerProduct.searhProduct(mainWindowAdmin.getIdToTableProducts()));
			actualPage = 1;
			refreshList(2);
			addProductDialog.setVisible(false);
			addProductDialog.changeActionToProductAdd();
			ReadXML.writeProduct(managerProduct.getListProducts());
		} catch (NumberFormatException | ErrorOrderNotFound e) {
			e.printStackTrace();
		}
	}

	private void deleteUser() throws TransformerException, ParserConfigurationException {
		try {
			manageruser.deleteUser(manageruser.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			ReadXML.writeUser(manageruser.getUsersList());
		} catch (ErrorUserNotFound e) {
			e.printStackTrace();
		}
	}

	private void deleteShop() throws TransformerException, ParserConfigurationException {
		try {
			managerShop.delteShop(managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			refreshList(0);
			ReadXML.writeShop(managerShop.getListShop());
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}

	//Metodos para refrescar las listas de las entidades
	private void refrehAllData() throws ParserConfigurationException, SAXException, IOException {
		refreshDataUser(ReadXML.readUser());
    	refreshDataShop(ReadXML.readShop());
    	refreshDataProduct(ReadXML.readProduct());
    	refreshDataAssigmentProductShop(ReadXML.readAsigmentProducts());
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
	
	private void refreshDataUser(ArrayList<User> readUser) {
		for (User user : readUser) {
			manageruser.addUser(user);
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

	private void addProduct() throws NumberFormatException, ParserConfigurationException, SAXException, IOException, TransformerException {
		managerProduct.addProduct(addProductDialog.extractProductFromWindow());
		cancelProduct();
		ReadXML.writeProduct(managerProduct.getListProducts());
		mainWindowAdmin.showMessageDialog("Product Added Successfully");
		actualPage = 1;
		refreshList(2);
	}
	
	private void addUser() throws TransformerException, ParserConfigurationException {
		manageruser.addUser(addUserDialog.getUser());
		addUserDialog.cleanForm();
		addUserDialog.setVisible(false);
		ReadXML.writeUser(manageruser.getUsersList());
		mainWindowAdmin.showMessageDialog("Se ha añadido el usuario con exito");
		actualPage = 1;
		refreshList(1);
	}

	private void addShop() throws TransformerException, ParserConfigurationException, SAXException, IOException {
		managerShop.addShop(addShopDialog.getShop());
		addShopDialog.cleanForm();
		addShopDialog.setVisible(false);
		ReadXML.writeShop(managerShop.getListShop());
		mainWindowAdmin.showMessageDialog("Se ha añadido la tienda con exito");
		actualPage = 1;
		refreshList(0);
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