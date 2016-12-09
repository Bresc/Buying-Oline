package controller;

/*
 * El proyecto esta dividido en 4 controladores este controlador maneja las acciones de la vista del administrador y las ejecuta 
 */
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
import view.login.LoginMainWindow;

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
	private LoginMainWindow dialogLogin;
	public static final int PAGE_SIZE = 10;

	public ControllerAdmin(GeneralController generalController, ManagerShop managerShop, ManagerUser managerUser,
			ManagerAsingProduct managerAsingProduct, LoginMainWindow dialogLogin) {
		this.generalController = generalController;
		this.managerShop = managerShop;
		this.managerUser = managerUser;
		this.dialogLogin = dialogLogin;
		mainWindowAdmin = new MainWindowAdmin(this);
		managerProduct = new ManagerProduct();
		this.managerAsingProduct = managerAsingProduct;
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
			addUser();
			break;
		case ADD_PRODUCT:
			addProduct();
			break;
		case ADD_SHOP:
			addShop();
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
		case EDIT_PRODUCT_TO_SHOP:
			break;
		case LOG_OUT:
			managerLogOut();
			break;
		}
	}

	private void managerLogOut() {
		mainWindowAdmin.setVisible(false);
		dialogLogin.setVisible(true);
		dialogLogin.clearLoginDialog();
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

	// Metodos de editar
	private void editShop() {
		try {
			managerShop.editShop(addShopDialog.getShop(), managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			refreshList(0);
			addShopDialog.setVisible(false);
			addShopDialog.changeActionToAddShop();
			ReadXML.writeShop(managerShop.getListShop());
			mainWindowAdmin.refreshTableShop(managerShop.getListShop());
		} catch (ErrorShopNotFound | ParserConfigurationException | SAXException | IOException
				| TransformerException e) {
			e.printStackTrace();
		}
	}

	private void editUser() {
		try {
			managerUser.editUser(addUserDialog.getUser(), managerUser.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			addUserDialog.setVisible(false);
			addUserDialog.changeActionToAddUser();
			ReadXML.writeUser(managerUser.getUsersList());
			mainWindowAdmin.refreshTableUser(managerUser.getUsersList());
		} catch (NumberFormatException | ErrorUserNotFound | TransformerException | ParserConfigurationException e) {
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
			ReadXML.writeProduct(managerProduct.getListProducts());
			mainWindowAdmin.refreshTableProducts(managerProduct.getListProducts());
		} catch (NumberFormatException | ErrorOrderNotFound | TransformerException | ParserConfigurationException
				| SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	// Metodos de borrar
	private void deleteUser() {
		try {
			managerUser.deleteUser(managerUser.searhUser(mainWindowAdmin.getIdToTableUser()));
			actualPage = 1;
			refreshList(1);
			ReadXML.writeUser(managerUser.getUsersList());
			mainWindowAdmin.refreshTableUser(managerUser.getUsersList());
		} catch (ErrorUserNotFound | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void deleteShop() {
		try {
			managerShop.delteShop(managerShop.searhShop(mainWindowAdmin.getIdToTableShops()));
			actualPage = 1;
			refreshList(0);
			ReadXML.writeShop(managerShop.getListShop());
			mainWindowAdmin.refreshTableShop(managerShop.getListShop());
		} catch (ErrorShopNotFound | TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void deleteProduct() {
		try {
			managerProduct.deleteProduct(managerProduct.searhProduct(mainWindowAdmin.getIdToTableProducts()));
			actualPage = 1;
			refreshList(2);
			ReadXML.writeProduct(managerProduct.getListProducts());
			mainWindowAdmin.refreshTableProducts(managerProduct.getListProducts());
		} catch (ErrorOrderNotFound | TransformerException | ParserConfigurationException e) {
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

	// Metodos de agregar
	private void addProduct() {
		try {
			managerProduct.addProduct(addProductDialog.extractProductFromWindow());
			cancelProduct();
			ReadXML.writeProduct(managerProduct.getListProducts());
			mainWindowAdmin.showMessageDialog("Product Added Successfully");
			actualPage = 1;
			refreshList(2);
			mainWindowAdmin.refreshTableProducts(managerProduct.getListProducts());
		} catch (NumberFormatException | ParserConfigurationException | SAXException | IOException
				| TransformerException e) {
			e.printStackTrace();
		}
	}

	private void addUser() {
		try {
			managerUser.addUser(addUserDialog.getUser());
			addUserDialog.cleanForm();
			addUserDialog.setVisible(false);
			ReadXML.writeUser(managerUser.getUsersList());
			mainWindowAdmin.showMessageDialog("Se ha añadido el usuario con exito");
			actualPage = 1;
			refreshList(1);
			mainWindowAdmin.refreshTableUser(managerUser.getUsersList());
		} catch (TransformerException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private void addShop() {
		try {
			managerShop.addShop(addShopDialog.getShop());
			addShopDialog.cleanForm();
			addShopDialog.setVisible(false);
			ReadXML.writeShop(managerShop.getListShop());
			mainWindowAdmin.showMessageDialog("Se ha añadido la tienda con exito");
			actualPage = 1;
			refreshList(0);
			mainWindowAdmin.refreshTableShop(managerShop.getListShop());
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			e.printStackTrace();
		}
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
				getTotalPages(returnListDependIndex(mainWindowAdmin.getTabbedPaneIndex())));
		switch (index) {
		case 0:
			mainWindowAdmin.refreshTableShop((ArrayList<Shop>) paginate(returnListDependIndex(index), actualPage));
			break;
		case 1:
			mainWindowAdmin.refreshTableUser((ArrayList<User>) paginate(returnListDependIndex(index), actualPage));
			break;
		case 2:
			mainWindowAdmin
					.refreshTableProducts((ArrayList<Product>) paginate(returnListDependIndex(index), actualPage));
			break;

		default:
			break;
		}
	}

	private void changeToNextPage() {
		if (actualPage < getTotalPages(returnListDependIndex(mainWindowAdmin.getTabbedPaneIndex()))) {
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

	/// Paginacion

	public ArrayList<?> paginate(ArrayList<?> list, int page) {
		int firstElement = (page - 1) * PAGE_SIZE;
		int lastElement = (page * PAGE_SIZE);
		lastElement = lastElement > list.size() ? list.size() : lastElement;
		return new ArrayList<>(list.subList(firstElement, lastElement));
	}

	public int getTotalPages(ArrayList<?> list) {
		int totalPages = list.size() / PAGE_SIZE;
		return (totalPages % PAGE_SIZE) > 0 ? ++totalPages : totalPages;
	}

	public ArrayList<?> returnListDependIndex(int index) {
		if (index == 0) {
			return managerShop.getListShop();
		} else if (index == 1) {
			return managerUser.getUsersList();
		} else {
			return managerProduct.getListProducts();
		}
	}

}