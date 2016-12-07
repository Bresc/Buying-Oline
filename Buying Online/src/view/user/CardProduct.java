package view.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.ControllerUser;
import models.entities.Product;

public class CardProduct extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lbNameProduct;
	private JLabel lbImgProduct;
	private JLabel lbPriceProduct;

	public CardProduct(ControllerUser controllerUser) {
		setLayout(new GridLayout(1, 2));
		setBorder(BorderFactory.createLineBorder(Color.decode("#076AC0")));
		setBackground(Color.WHITE);

		GridSystem gridPanel = new GridSystem(this);

		lbImgProduct = new JLabel();
		add(lbImgProduct, gridPanel.insertComponent(1, 1, 11, 1));

		lbNameProduct = new JLabel();
		lbNameProduct.setFont(new Font("Elephant", Font.ITALIC, 16));
		add(lbNameProduct, gridPanel.insertComponent(2, 1, 5, 0.15));

		lbPriceProduct = new JLabel();
		lbPriceProduct.setFont(new Font("Elephant", Font.ITALIC, 16));
		add(lbPriceProduct, gridPanel.insertComponent(2, 6, 5, 0.15));
	}

	public Icon loadImagen(String urlImage) {
		try {
			return new ImageIcon(new URL(urlImage));
		} catch (java.net.MalformedURLException e) {
			return new ImageIcon("src/img/loadingFail.png");
		}
	}

	public Icon reSize(Icon imagen) {
		Image conversion = ((ImageIcon) imagen).getImage();
		Image tamanio = conversion.getScaledInstance(300, 100, Image.SCALE_SMOOTH);
		ImageIcon result = new ImageIcon(tamanio);
		return result;
	}

	public void setForm(Product product) {
		lbImgProduct.setIcon(reSize(loadImagen(product.getSrcImg())));
		lbNameProduct.setText(product.getName());
		lbPriceProduct.setText(String.valueOf(product.getPrice()));
	}
}