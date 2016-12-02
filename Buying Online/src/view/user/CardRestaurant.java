package view.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Actions;
import controller.Controller;
import models.entities.Shop;

public class CardRestaurant extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lbNameRestaurant;
	private JButton btnImgRestaurant;

	public CardRestaurant(Controller controller) {
		setLayout(new GridLayout(1, 2));
		setBorder(BorderFactory.createLineBorder(Color.decode("#076AC0")));
		setBackground(Color.WHITE);

		GridSystem gridPanel = new GridSystem(this);

		btnImgRestaurant = new JButton(new ImageIcon());
		btnImgRestaurant.addActionListener(controller);
		btnImgRestaurant.setActionCommand(Actions.SHOW_PRODUCTS_BY_SHOP.toString());
		add(btnImgRestaurant, gridPanel.insertComponent(1, 1, 5, 0.70));

		lbNameRestaurant = new JLabel();
		lbNameRestaurant.setFont(new Font("Elephant", Font.ITALIC, 16));
		add(lbNameRestaurant, gridPanel.insertComponent(1, 6, 5, 0.15));
	}

	public Icon reSize(ImageIcon imagen) {
		Image conversion = imagen.getImage();
		Image tamanio = conversion.getScaledInstance(300, 70, Image.SCALE_SMOOTH);
		ImageIcon result = new ImageIcon(tamanio);
		return result;
	}

	public void setForm(Shop shop) {
		btnImgRestaurant.setIcon(reSize(new ImageIcon(shop.getSrcImg())));
		btnImgRestaurant.setName(String.valueOf(shop.getId()));
		lbNameRestaurant.setText(shop.getName());
	}
}