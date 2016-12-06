package view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import controller.ControllerUser;
import models.entities.Shop;

public class PanelShop extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridSystem gridFile;
	private ControllerUser controllerUser;
	private int rowPosition;

	public PanelShop(ControllerUser controllerUser) {
		this.controllerUser = controllerUser;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		gridFile = new GridSystem(this);
		rowPosition = 0;
	}

	public void refreshCardRestaurant(ArrayList<Shop> shops) {
		removeAll();
		for (Shop shop : shops) {
			CardRestaurant card = new CardRestaurant(controllerUser);
			card.setForm(shop);
			add(card, gridFile.insertComponent(rowPosition, 1, 3, 1));		
			rowPosition++;
		}
	}
}