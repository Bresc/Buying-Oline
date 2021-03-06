package view.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.ActionsAdmin;
import controller.ConstantUI;
import controller.ControllerAdmin;
import models.dao.ManagerProduct;
import models.entities.Product;
import view.product.ConstantUIProduct;
import view.user.GridSystem;

public class AddProductDialog extends JDialog {

	private char CHAR_COMA = ',';
	private char CHAR_POINT = '.';

	private static final long serialVersionUID = 1L;
	private JTextField textName;
	private JFormattedTextField textValue;
	private JLabel labelImage;
	private JButton btnChargeImage;
	private JFileChooser chooseImage;
	private JButton btnAceptProduct;
	private JButton btnCancelProduct;

	public AddProductDialog(MainWindowAdmin mainFrame, ControllerAdmin controller) {
		super(mainFrame, true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle("Add a new Product");
		setSize(500, 400);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);

		GridSystem gridProduct = new GridSystem(this);

		textName = new JTextField();
		textName.setBorder(BorderFactory.createTitledBorder("Product's Name"));
		add(textName, gridProduct.insertComponent(0, 1, 10, 0.001));

		NumberFormat format = NumberFormat.getIntegerInstance();
		format.setParseIntegerOnly(true);
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(-1);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(false);

		textValue = new JFormattedTextField(formatter);
		textValue.setBorder(BorderFactory.createTitledBorder("Product's Value"));
		add(textValue, gridProduct.insertComponent(1, 1, 10, 0.001));

		chooseImage = new JFileChooser();
		chooseImage.setCurrentDirectory(new File("./src/img"));
		
		btnChargeImage = new JButton("Charge Image");
		btnChargeImage.setBackground(ConstantUIAdmin.COLOR_BACKGROUND_BTN);
		btnChargeImage.setForeground(Color.BLACK);
		btnChargeImage.setActionCommand(ActionsAdmin.CHARGE_IMAGE_PRODUCT.toString());
		btnChargeImage.addActionListener(controller);
		add(btnChargeImage, gridProduct.insertComponent(2, 3, 6, 0.001));

		labelImage = new JLabel();
		labelImage.setIcon(new ImageIcon(ConstantUIProduct.DEFAULT_PRODUCT_IMAGE));
		add(labelImage, gridProduct.insertComponent(3, 5, 4, 0.01));

		btnAceptProduct = new JButton();
		btnAceptProduct.setBackground(ConstantUIAdmin.COLOR_BACKGROUND_BTN);
		btnAceptProduct.setForeground(Color.black);
		changeActionToProductAdd();
		btnAceptProduct.addActionListener(controller);
		btnAceptProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnAceptProduct, gridProduct.insertComponent(4, 3, 3, 0.001));

		btnCancelProduct = new JButton("Cancel");
		btnCancelProduct.setBackground(ConstantUIAdmin.COLOR_BACKGROUND_BTN);
		btnCancelProduct.setForeground(Color.black);
		btnCancelProduct.setActionCommand(ActionsAdmin.CANCEL_PRODUCT.toString());
		btnCancelProduct.addActionListener(controller);
		btnCancelProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(btnCancelProduct, gridProduct.insertComponent(4, 7, 3, 0.001));

		add(Box.createRigidArea(new Dimension(0, 10)));
	}

	public Product extractProductFromWindow()
			throws NumberFormatException, ParserConfigurationException, SAXException, IOException {
		return ManagerProduct.createProduct(textName.getText(),
				Double.parseDouble(intergerFormatter(textValue.getText())), String.valueOf(chooseImage.getSelectedFile()));
	}

	public void changeActionToProductEdit() {
		btnAceptProduct.setText("Edit Product");
		btnAceptProduct.setActionCommand(ActionsAdmin.EDIT_PRODUCT.name());
	}

	public void changeActionToProductAdd() {
		btnAceptProduct.setText("Add Product");
		btnAceptProduct.setActionCommand(ActionsAdmin.ADD_PRODUCT.name());
	}

	public String intergerFormatter(String num) {
		char letter;
		String numString = "";
		for (int i = 0; i < num.length(); i++) {
			letter = num.charAt(i);
			if (letter != CHAR_COMA && letter != CHAR_POINT) {
				numString += letter;
			}
		}
		return numString;
	}

	public void openFileChooser() {
		int option = chooseImage.showOpenDialog(this);
		if (option == JFileChooser.APPROVE_OPTION) {
			loadImage(chooseImage.getSelectedFile());
		}
	}
	
	public void chargeProductInWindow(Product product) {
		textName.setText(product.getName());
		textValue.setText("" + product.getPrice());
		labelImage.setIcon(loadImage(new File(product.getSrcImg())));
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
		labelImage.setIcon(reSize(imageLoaded));
		return imageLoaded;
	}
	
	public Icon reSize(ImageIcon imagen) {
		Image conversion = imagen.getImage();
		Image tamanio = conversion.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
		ImageIcon result = new ImageIcon(tamanio);
		return result;
	}

	public void cleanForm() {
		textName.setText("");
		textValue.setText("");
		labelImage.setIcon(new ImageIcon(ConstantUI.DEFAULT_PRODUCT_IMAGE));
	}
}