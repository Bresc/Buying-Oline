package view.shop;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import controller.Actions;
import controller.Controller;
import models.dao.AdminManager;
import models.entities.Product;
import view.admin.MainWindowAdmin;

public class AddProductFromShopViewDialog extends JDialog{
	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JLabel lbImage;
	private JButton btnSave;
	private String imgSource;
	private JTextField txtPriceProduct;
	private JFileChooser fileChooser;
	
	public AddProductFromShopViewDialog(MainWindowAdmin mainWindowAdmin, Controller controller) {
		super(mainWindowAdmin, true);
		setTitle("Student dialog");
		setSize(400, 370);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().setBackground(Color.WHITE);
		
		txtName = new JTextField();
		txtName.setBorder(BorderFactory.createTitledBorder("Name"));
		add(txtName);
		
		txtPriceProduct = new JTextField();
		txtPriceProduct.setBorder(BorderFactory.createTitledBorder("Price"));
		add(txtPriceProduct);
			
		JPanel pnlChooseImage = new JPanel(new GridLayout(1, 2));
		pnlChooseImage.setBackground(Color.WHITE);
		
		imgSource = "src/img/userIcon.png";
		lbImage = new JLabel(new ImageIcon(imgSource));
		lbImage.setBorder(BorderFactory.createTitledBorder("Drag your image here"));
		lbImage.setSize(40, 50);
		lbImage.setDropTarget(new DropTarget(){
			private static final long serialVersionUID = 1L;
			@Override
			public synchronized void drop(DropTargetDropEvent dtde) {
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				try {
					addImage(dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor).toString().replace("[", "").replace("]", "").replace("\\", "/"));
				} catch (UnsupportedFlavorException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		});
		pnlChooseImage.add(lbImage);
		JButton btnChooseImage = new JButton("Choose image");
		btnChooseImage.addActionListener(controller);
		btnChooseImage.setActionCommand(Actions.CHARGE_IMAGE_PRODUCT_FROM_SHOP_VIEW.toString());
		btnChooseImage.setBackground(Color.decode("#ccd9ff"));
		
		JPanel pnlRightChooseImage = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 70));
		pnlRightChooseImage.setBackground(Color.WHITE);
		pnlRightChooseImage.add(btnChooseImage);
		pnlChooseImage.add(pnlRightChooseImage);
		add(pnlChooseImage);

		btnSave = new JButton("Save", new ImageIcon("src/img/saveIcon.png"));
		btnSave.addActionListener(controller);
		btnSave.setActionCommand(Actions.ADD_PRODUCT_TO_SHOP.toString());
		btnSave.setBackground(Color.decode("#ccd9ff"));
		btnSave.setAlignmentX(CENTER_ALIGNMENT);
		add(btnSave);
	}
	
	
	public Product getCreatedProduct() {
		return AdminManager.createProduct(txtName.getText(), Double.parseDouble(txtPriceProduct.getText()), imgSource);
	}

	public void addImage(String newSourceImg) {
		imgSource = newSourceImg;
		Image img = new ImageIcon(newSourceImg).getImage().getScaledInstance( 150, -10, java.awt.Image.SCALE_AREA_AVERAGING);
		lbImage.setIcon(new ImageIcon(img));
	}
	
	public void cleanFields() {
		txtName.setText("");
		lbImage.setIcon(new ImageIcon(imgSource));
		txtPriceProduct.setText("");
	}
	
	public void refill(String name, String imgSource, double priceProduct) {
		txtName.setText(name);
		txtPriceProduct.setText(String.valueOf(priceProduct));
		addImage(imgSource);
	}
	
	public void showToEdit(Product productFromShopToEdit) {
		refill(productFromShopToEdit.getName(), productFromShopToEdit.getSrcImg(),
				productFromShopToEdit.getPrice());
		btnSave.setText("Edit");
		btnSave.setActionCommand(Actions.EDIT_PRODUCT_TO_SHOP.toString());
	}
	
	public void showToAddProduct() {
		cleanFields();
		btnSave.setText("Save");
		btnSave.setActionCommand(Actions.ADD_PRODUCT_TO_SHOP.toString());
	}
	
}