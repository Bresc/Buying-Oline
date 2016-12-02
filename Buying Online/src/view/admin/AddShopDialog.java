package view.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.Actions;
import controller.Controller;
import models.dao.AdminManager;
import models.entities.Shop;
import view.user.GridSystem;

public class AddShopDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField txName;
	private JFileChooser fcLoadImage;
	private JLabel minImage;
	private JButton btnAddShop;

	public AddShopDialog(MainWindowAdmin mainWindowAdmin, Controller controller) {
		super(mainWindowAdmin, true);
		setTitle("Add Shop");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().setBackground(Color.WHITE);

		GridSystem gridDialog = new GridSystem(this);

		txName = new JTextField();
		txName.setBorder(BorderFactory.createTitledBorder("Name:"));
		add(txName, gridDialog.insertComponent(0, 1, 10, 0.1));

		fcLoadImage = new JFileChooser();
		fcLoadImage.setCurrentDirectory(new File("./src/img"));

		add(Box.createRigidArea(new Dimension(0, 10)));

		JButton btImage = new JButton("Add imagen");
		btImage.addActionListener(controller);
		btImage.setActionCommand(Actions.ADD_IMAGE_TO_SHOP.toString());
		btImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btImage, gridDialog.insertComponent(1, 4, 3, 0.01));

		add(Box.createRigidArea(new Dimension(0, 10)));

		minImage = new JLabel(new ImageIcon("src/img/DefaultImage.png"));
		minImage.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(minImage, gridDialog.insertComponent(2, 4, 3, 0.1));

		btnAddShop = new JButton("Accept");
		btnAddShop.addActionListener(controller);
		btnAddShop.setActionCommand(Actions.ADD_SHOP.toString());
		btnAddShop.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnAddShop, gridDialog.insertComponent(3, 2, 7, 0.01));
	}

	public void changeActionToEditShop() {
		btnAddShop.setText("Edit Shop");
		btnAddShop.setActionCommand(Actions.EDIT_SHOP.name());
	}
	
	public void changeActionToAddShop() {
		btnAddShop.setText("Add Shop");
		btnAddShop.setActionCommand(Actions.ADD_SHOP.name());
	}
	
	public Icon reSize(ImageIcon imagen) {
		Image conversion = imagen.getImage();
		Image tamanio = conversion.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
		ImageIcon result = new ImageIcon(tamanio);
		return result;
	}

	public void openFileChooser() {
		int option = fcLoadImage.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			loadImage(fcLoadImage.getSelectedFile());
		}
	}

	public ImageIcon loadImage(File file) {
		BufferedImage image = null;
		ImageIcon imageLoaded;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		imageLoaded = new ImageIcon(image);
		minImage.setIcon(reSize(imageLoaded));
		return imageLoaded;
	}

	public Shop getShop() {
		Shop.sumShopId();
		return AdminManager.createShop(txName.getText(), String.valueOf(fcLoadImage.getSelectedFile()));
	}

	public void setForm(Shop shop) {
		txName.setText(shop.getName());
		try {
			minImage.setIcon(loadImage(new File(shop.getSrcImg())));
		} catch (Exception e) {
			minImage.setIcon(new ImageIcon("src/img/DefaultImage.png"));
		}		
	}

	public void cleanForm() {
		txName.setText("");
		minImage.setIcon(new ImageIcon("src/img/DefaultImage.png"));
	}
}