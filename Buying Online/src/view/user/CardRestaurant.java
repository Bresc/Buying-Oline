package view.user;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.ActionsUser;
import controller.ControllerUser;
import models.entities.Shop;

public class CardRestaurant extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel lbNameRestaurant;
	private JButton btnImgRestaurant;

	public CardRestaurant(ControllerUser controllerUser) {
		setBorder(BorderFactory.createLineBorder(ConstanstUIUser.COLOR_LINE_BORDER));
		setBackground(Color.WHITE);

		GridSystem gridPanel = new GridSystem(this);

		btnImgRestaurant = new JButton(new ImageIcon());
		btnImgRestaurant.setBorder(null);
		btnImgRestaurant.addActionListener(controllerUser);
		btnImgRestaurant.setActionCommand(ActionsUser.SHOW_PRODUCTS_BY_SHOP.toString());
		add(btnImgRestaurant, gridPanel.insertComponent(1, 1, 5, 0.70));

		lbNameRestaurant = new JLabel();
		lbNameRestaurant.setFont(ConstanstUIUser.FONT_USER_WINDOW);
		add(lbNameRestaurant, gridPanel.insertComponent(2, 1, 5, 0.15));
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
		Image tamanio = conversion.getScaledInstance(300, 70, Image.SCALE_SMOOTH);
		ImageIcon result = new ImageIcon(tamanio);
		return result;
	}

	public void setForm(Shop shop) {
		btnImgRestaurant.setIcon(reSize(loadImagen(shop.getSrcImg())));
		btnImgRestaurant.setName(String.valueOf(shop.getId()));
		lbNameRestaurant.setText(shop.getName());
	}
}