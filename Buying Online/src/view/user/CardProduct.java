package view.user;

import java.awt.Color;
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
		setBorder(BorderFactory.createLineBorder(ConstanstUIUser.COLOR_LINE_BORDER));
		setBackground(Color.WHITE);

		GridSystem gridPanel = new GridSystem(this);

		lbImgProduct = new JLabel();
		add(lbImgProduct, gridPanel.insertComponent(1, 1, 11, 1));

		lbNameProduct = new JLabel();
		lbNameProduct.setFont(ConstanstUIUser.FONT_USER_WINDOW);
		add(lbNameProduct, gridPanel.insertComponent(2, 1, 5, 0.15));

		lbPriceProduct = new JLabel();
		lbPriceProduct.setFont(ConstanstUIUser.FONT_USER_WINDOW);
		add(lbPriceProduct, gridPanel.insertComponent(2, 6, 5, 0.15));
	}

	public Icon loadImagen(String urlImage) {
		try {
			return new ImageIcon(new URL(urlImage));
		} catch (java.net.MalformedURLException e) {
			return ConstanstUIUser.IMAGE_FAIL_LOADING;
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