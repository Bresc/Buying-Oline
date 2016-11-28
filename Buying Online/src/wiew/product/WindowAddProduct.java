package wiew.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import controller.Actions;
import controller.ConstantUI;
import controller.Controller;
import models.entities.Product;
import view.admin.MainWindowAdmin;

public class WindowAddProduct extends JDialog{
	
	private static final long serialVersionUID = 1L;
	private JSpinner spId;
	private JTextField textName;
	private JTextField textValue;
	private JLabel labelImage;
	private JButton btnChargeImage;
	private JFileChooser chooseImage;
	private JPanel panelButtonsProduct;
	private JPanel panelButtonsEdit;
	private JButton btnAceptProduct;
	private JButton btnCancelProduct;

	public WindowAddProduct(MainWindowAdmin mainFrame,Controller controller) {
		super(mainFrame,true);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setTitle("Add a new Product");
		setSize(330, 460);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.white);
		
		//(componentes))
		spId = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
		spId.setBorder(BorderFactory.createTitledBorder("Id"));
		spId.setBackground(Color.white);
		add(spId);
		
		textName= new JTextField();
		textName.setBorder(BorderFactory.createTitledBorder("Product's Name"));
		add(textName);
		
		textValue = new JTextField();
		textValue.setBorder(BorderFactory.createTitledBorder("Product's Value"));
		add(textValue);
		
		JPanel panelImage = new JPanel();
		panelImage.setLayout(new BorderLayout());
		panelImage.setBorder(BorderFactory.createTitledBorder("Product's Image"));
		
		labelImage = new JLabel();
		labelImage.setIcon(new ImageIcon(new ImageIcon(ConstantUI.DEFAULT_PRODUCT_IMAGE).getImage().getScaledInstance(70, 70, 100)));
		
		panelImage.add(labelImage,BorderLayout.CENTER);
		add(panelImage);
		
		btnChargeImage = new JButton("Charge An Image");
		btnChargeImage.setForeground(Color.white);
		btnChargeImage.setActionCommand(Actions.CHARGE_IMAGE.toString());
		btnChargeImage.addActionListener(controller);
		
		panelImage.add(btnChargeImage, BorderLayout.EAST);
		
		add(panelImage);
		
		panelButtonsProduct = new JPanel();
		panelButtonsProduct.setLayout(new FlowLayout());
		
		panelButtonsEdit = new JPanel();
		panelButtonsEdit.setLayout(new FlowLayout());
				
		btnAceptProduct  = new JButton("Ad");
		btnAceptProduct.setForeground(Color.white);
		btnAceptProduct.setActionCommand(Actions.ADD_PRODUCT.toString());
		btnAceptProduct.addActionListener(controller);
		btnAceptProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		btnCancelProduct  = new JButton("Cancel");
		btnCancelProduct.setForeground(Color.white);
		btnCancelProduct.setActionCommand(Actions.CANCEL_PRODUCT.toString());
		btnCancelProduct.addActionListener(controller);
		btnCancelProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		panelButtonsProduct.add(btnAceptProduct);
		panelButtonsProduct.add(btnCancelProduct);

		add(panelButtonsProduct);
		
		add(Box.createRigidArea(new Dimension(0,10))); //espacio en blanco
			}

//	public Product extractProductFromWindow() throws NumberFormatException {		
//		return ProductManager.createProduct((Integer)spId.getValue(),
//				textName.getText(), 
//				Double.parseDouble(textValue.getText()),
//				getImageInChooser());
//	}
	
	public void chargeProductInWindow(Product product){
		spId.setValue(product.getId());
		textName.setText(product.getName());
		textValue.setText("" + product.getPrice());
		labelImage.setIcon(new ImageIcon(new ImageIcon(product.getSrcImg()).getImage().getScaledInstance(150, -10, Image.SCALE_AREA_AVERAGING)));
	}
	
	
	public void searchForImage(){
		chooseImage = new JFileChooser("src/images");
		chooseImage.showOpenDialog(this);
//		chooseImage.setSelectedFile(new File(ConstantUI.DEFAULT_PRODUCT_IMAGE));
		String image = getImageInChooser();
		labelImage.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(image).getImage().getScaledInstance(150, -10, java.awt.Image.SCALE_AREA_AVERAGING);
		this.labelImage.setIcon(new ImageIcon(img));
	}
	
	public String getImageInChooser(){
		return chooseImage.getSelectedFile().toString();
	}
	
	public void changeImageInLabel(String urlImage){
		labelImage.setIcon(new ImageIcon(new ImageIcon(urlImage).getImage().getScaledInstance(30, 30, 100)));
	}

	public void cleanForm() {
		spId.setValue(0);
		textName.setText("");
		textValue.setText("");	
		labelImage.setIcon(new ImageIcon(new ImageIcon(ConstantUI.DEFAULT_PRODUCT_IMAGE).getImage().getScaledInstance(150, -10, Image.SCALE_AREA_AVERAGING)));
	}

	public JSpinner getSpId() {
		return spId;
	}

	public JPanel getPanelButtonsProduct() {
		return panelButtonsProduct;
	}

	public void setPanelButtonsProduct(JPanel panelButtonsProduct) {
		this.panelButtonsProduct = panelButtonsProduct;
	}


}
