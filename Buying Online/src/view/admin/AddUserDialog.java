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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Actions;
import controller.Controller;
import models.dao.AdminManager;
import models.entities.Shop;
import models.entities.User;
import view.user.GridSystem;


public class AddUserDialog extends JDialog{

	private static final long serialVersionUID = 1L;
	private JFileChooser fcLoadImage;
	private JLabel minImage;
	private JTextField txName;
	private JTextField txAddress;
	private JPasswordField txPassword;

	public AddUserDialog(MainWindowAdmin mainWindowAdmin, Controller controller){
		super(mainWindowAdmin, true);
		setTitle("Add User");
		setSize(500, 400);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(),
				BoxLayout.Y_AXIS));
		getContentPane().setBackground(Color.WHITE);
		
		GridSystem gridDialog = new GridSystem(this);
		
		txName = new JTextField();
		txName.setBorder(BorderFactory.createTitledBorder("Name:"));
		add(txName, gridDialog.insertComponent(0, 1, 10, 0.1));
		
		txAddress = new JTextField();
		txAddress.setBorder(BorderFactory.createTitledBorder("Address:"));
		add(txAddress, gridDialog.insertComponent(1, 1, 10, 0.1));
		
		txPassword = new JPasswordField();
		txPassword.setBorder(BorderFactory.createTitledBorder("Password:"));
		add(txPassword, gridDialog.insertComponent(2, 1, 10, 0.1));
		
		fcLoadImage = new JFileChooser();
		
		add(Box.createRigidArea(new Dimension(0, 10)));
		
		JButton btImage = new JButton("Add imagen"); 
		btImage.addActionListener(controller);
		btImage.setActionCommand(Actions.ADD_IMAGE_TO_USER.toString());
		btImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btImage, gridDialog.insertComponent(3, 4, 3, 0.01));
		
		add(Box.createRigidArea(new Dimension(0, 10)));
		
		minImage = new JLabel(new ImageIcon("src/img/DefaultImage.png"));
		minImage.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(minImage, gridDialog.insertComponent(4, 4, 3, 0.1));
		
		JButton btnAddUser = new JButton("Accept");
		btnAddUser.addActionListener(controller);
		btnAddUser.setActionCommand(Actions.ADD_USER.toString());
		btnAddUser.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnAddUser, gridDialog.insertComponent(5, 2, 7, 0.01));
	}
	
	public Icon reSize(ImageIcon imagen){
		Image convertion = imagen.getImage();
		Image size = convertion.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
		ImageIcon result = new ImageIcon(size);
		return result;
	}

	public void openFileChooser(){
		int option = fcLoadImage.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			loadImage(fcLoadImage.getSelectedFile());
		}
	}
	
	public ImageIcon loadImage(File file){
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
	
	public User getUser(){
		return AdminManager.createUser(txName.getText(), txAddress.getText(), txPassword.getText(), String.valueOf(fcLoadImage.getSelectedFile()));
	}
	
	public void setForm(User user){
		txName.setText(user.getName());
		txAddress.setText(user.getAddress());
		txPassword.setText(user.getPassword());
		minImage.setIcon(loadImage(new File(user.getSourceImg())));
	}
	
	public void cleanForm(){
		txName.setText("");
		txAddress.setText("");
		txPassword.setText("");
		minImage.setIcon(new ImageIcon("src/img/DefaultImage.png"));
	}
}