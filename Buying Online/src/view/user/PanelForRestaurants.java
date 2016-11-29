package view.user;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import controller.Controller;

public class PanelForRestaurants extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridSystem gridFile;

	public PanelForRestaurants(Controller controller) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		gridFile = new GridSystem(this);
	}

	public void refreshCardRestaurant(Controller controller) {
		removeAll();
		for (int i = 0; i < 5; i++) {
			CardRestaurant card = new CardRestaurant(controller);
			card.setForm();
			add(card, gridFile.insertComponent(i, 1, 3, 1));
		}
	}
}