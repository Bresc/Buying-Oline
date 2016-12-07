package view.user;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import controller.ActionsUser;
import controller.ControllerUser;
import models.entities.Product;

public class PanelProductsFromShop extends JPanel {

	private static final long serialVersionUID = 1L;
	private ControllerUser controllerUser;
	private GridSystem gridFile;
	private int rowPosition;

	public PanelProductsFromShop(ControllerUser controllerUser) {
		this.controllerUser = controllerUser;
		setLayout(new BorderLayout());
		gridFile = new GridSystem(this);
		rowPosition = 0;
	}

	public void refreshProducts(ArrayList<Product> productsFromShop) {
		removeAll();
		JButton btnBackWindow = new JButton(new ImageIcon(getClass().getResource("/img/Back.png")));
		btnBackWindow.setBorder(null);
		btnBackWindow.addActionListener(controllerUser);
		btnBackWindow.setActionCommand(ActionsUser.BACK_TO_PANEL_RESTAURANTS.toString());
		add(btnBackWindow, gridFile.insertComponent(rowPosition, 0, 1, 0.1));
		rowPosition++;
		for (Product product : productsFromShop) {
			CardProduct card = new CardProduct(controllerUser);
			card.setForm(product);
			add(card, gridFile.insertComponent(rowPosition, 1, 11, 1));
			rowPosition++;
		}
	}
}