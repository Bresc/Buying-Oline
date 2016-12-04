package view.user;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Actions;
import controller.Controller;
import models.entities.Product;

public class PanelProductsFromShop extends JPanel{

	private static final long serialVersionUID = 1L;
	Controller controller;
	private GridSystem gridFile;
	private int rowPosition;

	public PanelProductsFromShop(Controller controller) {
		this.controller = controller;
		setLayout(new BorderLayout());
		
		gridFile = new GridSystem(this);
		
		rowPosition = 0;
	}

	public void refreshProducts(ArrayList<Product> productsFromShop) {
		removeAll();
		JButton btnBackWindow = new JButton(new ImageIcon(getClass().getResource("/img/Back.png")));
		btnBackWindow.setBorder(null);
		btnBackWindow.addActionListener(controller);
		btnBackWindow.setActionCommand(Actions.BACK_TO_PANEL_RESTAURANTS.toString());
		add(btnBackWindow, gridFile.insertComponent(rowPosition, 0, 1, 0.1));
		rowPosition++;
		for (Product product : productsFromShop) {
			CardProduct card = new CardProduct(controller);
			card.setForm(product);
			add(card, gridFile.insertComponent(rowPosition, 1, 11, 1));		
			rowPosition++;
		}
	}
}
