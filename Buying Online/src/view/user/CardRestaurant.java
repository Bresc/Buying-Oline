package view.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;

public class CardRestaurant extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lbNameRestaurant;
	private JLabel lbImgRestaurant;

	public CardRestaurant(Controller controller){
		setLayout(new GridLayout(1, 2));
		setBorder(BorderFactory.createLineBorder(Color.decode("#076AC0")));
		setBackground(Color.WHITE);

		GridSystem gridPanel = new GridSystem(this);


		lbImgRestaurant = new JLabel(new ImageIcon());
		add(lbImgRestaurant, gridPanel.insertComponent(1, 1, 5, 0.70));


		lbNameRestaurant = new JLabel();
		lbNameRestaurant.setFont(new Font("Elephant", Font.ITALIC, 16));
		add(lbNameRestaurant, gridPanel.insertComponent(1, 6, 5, 0.15));

	}

	public Icon reSize(ImageIcon imagen){
		Image conversion = imagen.getImage();
		Image tamanio = conversion.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon result = new ImageIcon(tamanio);
		return result;
	}

	
	public void setForm(){
		lbImgRestaurant.setIcon(reSize(new ImageIcon("src/img/RestaurantExample.jpg")));
		lbNameRestaurant.setText("Restaurant 1");
	}
}