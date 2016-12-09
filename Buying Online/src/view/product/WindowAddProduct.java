package view.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import controller.ActionsAdmin;
import controller.ConstantUI;
import controller.ControllerAdmin;
import models.dao.ManagerProduct;
import models.entities.Product;
import view.admin.MainWindowAdmin;
import view.login.ConstantsUILogin;

public class WindowAddProduct extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textName;
	private JTextField textValue;
	private JLabel labelImage;
	private JButton btnChargeImage;
	private JFileChooser chooseImage;
	private JPanel panelButtonsProduct;
	private JPanel panelButtonsEdit;
	private JButton btnAceptProduct;
	private JButton btnCancelProduct;

	public WindowAddProduct(MainWindowAdmin mainFrame, ControllerAdmin controller) {
		super(mainFrame, true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setIconImage(new ImageIcon(getClass().getResource(ConstantsUILogin.IMG_ICON_LOGIN)).getImage());
		setTitle("Add a new Product");
		setSize(330, 460);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);

		textName = new JTextField();
		textName.setBorder(BorderFactory.createTitledBorder("Product's Name"));
		add(textName);

		textValue = new JTextField();
		textValue.setBorder(BorderFactory.createTitledBorder("Product's Value"));
		add(textValue);

		JPanel panelImage = new JPanel();
		panelImage.setLayout(new BorderLayout());
		panelImage.setBorder(BorderFactory.createTitledBorder("Product's Image"));

		labelImage = new JLabel();
		labelImage.setIcon(new ImageIcon(
				new ImageIcon(ConstantUIProduct.DEFAULT_PRODUCT_IMAGE).getImage().getScaledInstance(70, 70, 100)));

		panelImage.add(labelImage, BorderLayout.CENTER);
		add(panelImage);

		btnChargeImage = new JButton("Charge An Image");
		btnChargeImage.setForeground(Color.white);
		btnChargeImage.setActionCommand(ActionsAdmin.CHARGE_IMAGE_PRODUCT.toString());
		btnChargeImage.addActionListener(controller);

		panelImage.add(btnChargeImage, BorderLayout.EAST);

		add(panelImage);

		panelButtonsProduct = new JPanel();
		panelButtonsProduct.setLayout(new FlowLayout());

		panelButtonsEdit = new JPanel();
		panelButtonsEdit.setLayout(new FlowLayout());

		btnAceptProduct = new JButton("Add");
		btnAceptProduct.setForeground(Color.black);
		btnAceptProduct.setActionCommand(ActionsAdmin.ADD_PRODUCT.toString());
		btnAceptProduct.addActionListener(controller);
		btnAceptProduct.setAlignmentX(Component.CENTER_ALIGNMENT);

		btnCancelProduct = new JButton("Cancel");
		btnCancelProduct.setForeground(Color.black);
		btnCancelProduct.setActionCommand(ActionsAdmin.CANCEL_PRODUCT.toString());
		btnCancelProduct.addActionListener(controller);
		btnCancelProduct.setAlignmentX(Component.CENTER_ALIGNMENT);

		panelButtonsProduct.add(btnAceptProduct);
		panelButtonsProduct.add(btnCancelProduct);

		add(panelButtonsProduct);

		add(Box.createRigidArea(new Dimension(0, 10))); // espacio en blanco
	}

	public Product extractProductFromWindow()
			throws NumberFormatException, ParserConfigurationException, SAXException, IOException {
		return ManagerProduct.createProduct(textName.getText(), Double.parseDouble(textValue.getText()),
				getImageInChooser());
	}

	public void chargeProductInWindow(Product product) {
		textName.setText(product.getName());
		textValue.setText("" + product.getPrice());
		labelImage.setIcon(new ImageIcon(
				new ImageIcon(product.getSrcImg()).getImage().getScaledInstance(150, -10, Image.SCALE_AREA_AVERAGING)));
	}

	public void searchForImage() {
		chooseImage = new JFileChooser("src/img");
		chooseImage.showOpenDialog(this);
		// chooseImage.setSelectedFile(new
		// File(ConstantUI.DEFAULT_PRODUCT_IMAGE));
		String image = getImageInChooser();
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(image).getImage().getScaledInstance(150, -10, java.awt.Image.SCALE_AREA_AVERAGING);
		this.labelImage.setIcon(new ImageIcon(img));
	}

	public String getImageInChooser() {
		return chooseImage.getSelectedFile().toString();
	}

	public void changeImageInLabel(String urlImage) {
		labelImage.setIcon(new ImageIcon(new ImageIcon(urlImage).getImage().getScaledInstance(30, 30, 100)));
	}

	public void cleanForm() {
		textName.setText("");
		textValue.setText("");
		labelImage.setIcon(new ImageIcon(new ImageIcon(ConstantUI.DEFAULT_PRODUCT_IMAGE).getImage()
				.getScaledInstance(150, -10, Image.SCALE_AREA_AVERAGING)));
	}

	public JPanel getPanelButtonsProduct() {
		return panelButtonsProduct;
	}

	public void setPanelButtonsProduct(JPanel panelButtonsProduct) {
		this.panelButtonsProduct = panelButtonsProduct;
	}
}