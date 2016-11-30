package view.user;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import models.entities.Shop;

public class PanelShop extends JPanel {

	private static final long serialVersionUID = 1L;
	private GridSystem gridFile;
	private JLabel labelImge;
	private JLabel labelName;

	public PanelShop(Shop shop, Controller controller) {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		labelImge = new JLabel(new ImageIcon(shop.getSrcImg()));
		labelName = new JLabel(shop.getName());
		
		add(labelImge, BorderLayout.CENTER);
		add(labelName, BorderLayout.SOUTH);
		
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