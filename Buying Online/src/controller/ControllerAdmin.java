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

public class ControllerAdmin implements ActionListener, KeyListener, ChangeListener {

	private MainWindowAdmin mainWindowAdmin;
	private ManagerAsingProduct managerAsingProduct;
	private ManagerProduct managerProduct;
	private ManagerShop managerShop;
	private ManagerUser managerUser;
	private AddUserDialog addUserDialog;
	private AddProductDialog addProductDialog;
	private AddShopDialog addShopDialog;
	private GeneralController generalController;
	private int actualPage;

	public ControllerAdmin(GeneralController generalController) {
		this.generalController = generalController;
		mainWindowAdmin = new MainWindowAdmin(this);
		managerUser = new ManagerUser();
		managerShop = new ManagerShop();
		managerProduct = new ManagerProduct();
		managerAsingProduct = new ManagerAsingProduct();
		
		addProductDialog = new AddProductDialog(mainWindowAdmin, this);
		addUserDialog = new AddUserDialog(mainWindowAdmin, this);
		addShopDialog = new AddShopDialog(mainWindowAdmin, this);

		actualPage = 1;
		refreshList(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (ActionsAdmin.valueOf(e.getActionCommand())) {
		case ADD_USER:
			try {
				addUser();
			} catch (TransformerException | ParserConfigurationException e6) {
				e6.printStackTrace();
			}
			break;
		case ADD_PRODUCT:
			try {
				addProduct();
			} catch (NumberFormatException | ParserConfigurationException | SAXException | IOException
					| TransformerException e5) {
				e5.printStackTrace();
			}
			break;
		case ADD_SHOP:
			try {
				addShop();
			} catch (TransformerException | ParserConfigurationException | SAXException | IOException e4) {
				e4.printStackTrace();
			}
			
			break;
		case CANCEL_PRODUCT:
			cancelProduct();
			break;
		case ADD_IMAGE_TO_SHOP:
			addShopDialog.openFileChooser();
			break;
		case ADD_IMAGE_TO_USER:
			addUserDialog.openFileChooser();
			break;
		case CHARGE_IMAGE_PRODUCT:
			addProductDialog.openFileChooser();
			break;
		case SHOW_ADD_USER_DIALOG:
			addUserDialog.setVisible(true);
			break;
		case SHOW_ADD_SHOP_DIALOG:
			addShopDialog.setVisible(true);
			break;
		case SHOW_ADD_PRODUCT_DIALOG:
			addProductDialog.setVisible(true);
			break;
		case GO_LEFT_ARROW:
			changeToPreviousPage();
			break;
		case GO_RIGHT_ARROW:
			changeToNextPage();
			break;
		case DELETE_PRODUCT:
			try {
				deleteProduct();
			} catch (TransformerException | ParserConfigurationException e4) {
				// TODO Auto-generated catch block
				e4.printStackTrace();
			}
			break;
		case DELETE_SHOP:
			try {
				deleteShop();
			} catch (TransformerException | ParserConfigurationException e3) {
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
				e1.printStackTrace();
			}
			break;
		case EDIT_SHOP:
			try {
				editShop();
			} catch (TransformerException | ParserConfigurationException | SAXException | IOException e1) {
				e1.printStackTrace();
			}
			break;
		case EDIT_USER:
			try {
				editUser();
			} catch (TransformerException | ParserConfigurationException e1) {
				e1.printStackTrace();
			}
			break;
		case SHOW_EDIT_USER:
			showEditUser();
			break;
		case SHOW_EDIT_SHOP:
			showEditShop();
			break;
		case EDIT_PRODUCT_TO_SHOP:
			break;
		case LOG_OUT:
			mainWindowAdmin.setVisible(false);
			break;
		}
	}

	private void showEditShop() {
		addShopDialog.changeActionToEditShop();
		try {
			addShopDialog.setForm(managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
		addShopDialog.setVisible(true);
	}

	private void showEditUser() {
		addUserDialog.changeActionToEditUser();
		try {
			addUserDialog.setForm(managerUser.searhUser(mainWindowAdmin.getIdToTableUser()));
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

	//Metodos de editar
	private void editShop() throws TransformerException, ParserConfigurationException, SAXException, IOException {
		try {
			managerShop.editShop(addShopDialog.getShop(), managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			refreshList(0);
			addShopDialog.setVisible(false);
			addShopDialog.changeActionToAddShop();
			ReadXML.writeShop(managerShop.getListShop());
			mainWindowAdmin.refreshTableShop(managerShop.getListShop());
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}

	private void editUser() throws TransformerException, ParserConfigurationException {
		try {
			managerUser.editUser(addUserDialog.getUser(), managerUser.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			addUserDialog.setVisible(false);
			addUserDialog.changeActionToAddUser();
			ReadXML.writeUser(managerUser.getUsersList());
			mainWindowAdmin.refreshTableUser(managerUser.getUsersList());
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
			mainWindowAdmin.refreshTableProducts(managerProduct.getListProducts());
		} catch (NumberFormatException | ErrorOrderNotFound e) {
			e.printStackTrace();
		}
	}

	//Metodos de borrar
	private void deleteUser() throws TransformerException, ParserConfigurationException {
		try {
			managerUser.deleteUser(managerUser.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			ReadXML.writeUser(managerUser.getUsersList());
			mainWindowAdmin.refreshTableUser(managerUser.getUsersList());
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
			mainWindowAdmin.refreshTableShop(managerShop.getListShop());
		} catch (ErrorShopNotFound e) {
			e.printStackTrace();
		}
	}
	
	private void deleteProduct() throws TransformerException, ParserConfigurationException {
		try {
			managerProduct.deleteProduct(managerProduct.searhProduct(mainWindowAdmin.getIdToTableProducts()));
			actualPage = 1;
			refreshList(2);
			ReadXML.writeProduct(managerProduct.getListProducts());
			mainWindowAdmin.refreshTableProducts(managerProduct.getListProducts());
		} catch (ErrorOrderNotFound e) {
			e.printStackTrace();
		}
	}


	// Metodos para refrescar las listas de las entidades
	public void refrehAllData() throws ParserConfigurationException, SAXException, IOException {
		refreshDataUser(ReadXML.readUser());
		refreshDataShop(ReadXML.readShop());
		refreshDataProduct(ReadXML.readProduct());
		refreshDataAssigmentProductShop(ReadXML.readAsigmentProducts());
	}

	private void refreshDataProduct(ArrayList<Product> readProduct) {
		managerProduct.clearList();
		managerProduct.addAllProducts(readProduct);
	}

	private void refreshDataAssigmentProductShop(ArrayList<AssignmentProductShop> readAssignmentProductShops) {
		managerAsingProduct.clearList();
		managerAsingProduct.addAllAssignmentProductShop(readAssignmentProductShops);
	}

	private void refreshDataShop(ArrayList<Shop> readShop) {
		managerShop.clearList();
		managerShop.addAllShop(readShop);
	}

	private void refreshDataUser(ArrayList<User> readUser) {
		managerUser.clearList();
		managerUser.addAllUser(readUser);
	}

	public User validateUser(String name, String password) throws ErrorUserNotFound {
		return managerUser.validateUserLogin(name, password);
	}
	
	//Metodos de agregar
	private void addProduct() throws NumberFormatException, ParserConfigurationException, SAXException, IOException,
			TransformerException {
		managerProduct.addProduct(addProductDialog.extractProductFromWindow());
		cancelProduct();
		ReadXML.writeProduct(managerProduct.getListProducts());
		mainWindowAdmin.showMessageDialog("Product Added Successfully");
		actualPage = 1;
		refreshList(2);
		mainWindowAdmin.refreshTableProducts(managerProduct.getListProducts());
	}

	private void addUser() throws TransformerException, ParserConfigurationException {
		managerUser.addUser(addUserDialog.getUser());
		addUserDialog.cleanForm();
		addUserDialog.setVisible(false);
		ReadXML.writeUser(managerUser.getUsersList());
		mainWindowAdmin.showMessageDialog("Se ha añadido el usuario con exito");
		actualPage = 1;
		refreshList(1);
		mainWindowAdmin.refreshTableUser(managerUser.getUsersList());
	}

	private void addShop() throws TransformerException, ParserConfigurationException, SAXException, IOException {
		managerShop.addShop(addShopDialog.getShop());
		addShopDialog.cleanForm();
		addShopDialog.setVisible(false);
		ReadXML.writeShop(managerShop.getListShop());
		mainWindowAdmin.showMessageDialog("Se ha añadido la tienda con exito");
		actualPage = 1;
		refreshList(0);
		mainWindowAdmin.refreshTableShop(managerShop.getListShop());
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
		mainWindowAdmin.refreshPage(actualPage, managerAsingProduct
				.getTotalPages(managerAsingProduct.returnListDependIndex(mainWindowAdmin.getTabbedPaneIndex())));
		switch (index) {
		case 0:
			mainWindowAdmin.refreshTableShop((ArrayList<Shop>) managerAsingProduct
					.paginate(managerAsingProduct.returnListDependIndex(index), actualPage));
			break;
		case 1:
			mainWindowAdmin.refreshTableUser((ArrayList<User>) managerAsingProduct
					.paginate(managerAsingProduct.returnListDependIndex(index), actualPage));
			break;
		case 2:
			mainWindowAdmin.refreshTableProducts((ArrayList<Product>) managerAsingProduct
					.paginate(managerAsingProduct.returnListDependIndex(index), actualPage));
			break;

		default:
			break;
		}
	}

	private void changeToNextPage() {
		if (actualPage < managerAsingProduct
				.getTotalPages(managerAsingProduct.returnListDependIndex(mainWindowAdmin.getTabbedPaneIndex()))) {
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

	public void setVisible() {
		mainWindowAdmin.refreshTableProducts(managerProduct.getListProducts());
		mainWindowAdmin.refreshTableShop(managerShop.getListShop());
		mainWindowAdmin.refreshTableUser(managerUser.getUsersList());
		mainWindowAdmin.setVisible(true);
	}

	public void showAddShopDialog() {
		addShopDialog.setVisible(true);
		generalController.LoginVisible();
	}
	public void showAddUserDialog() {
		addUserDialog.setVisible(true);
		generalController.LoginVisible();
	}
}