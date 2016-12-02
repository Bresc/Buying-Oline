package view.shop;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import models.entities.Product;

public class MainWindowShop extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private TableProductsByShop productsByShop;
	private TableOrders tableOrders;

	public MainWindowShop() {
		setTitle("Shop");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(450, 450);
		setLocationRelativeTo(null);
		
		JPanel panelDetails = new JPanel();
		panelDetails.setLayout(new BoxLayout(panelDetails, BoxLayout.X_AXIS));
		panelDetails.setBackground(Color.white);
		JLabel labelName = new JLabel("SUBWAY");
		labelName.setFont(new Font("Segoe UI Black", Font.ITALIC, 59));
		labelName.setForeground(Color.decode("#6699CC"));
		JLabel labeImage = new JLabel();
		labeImage.setIcon(new ImageIcon(new ImageIcon("src/img/Owner.png").getImage().getScaledInstance(150, 150, 150)));
		
		panelDetails.add(labelName);
		panelDetails.add(Box.createRigidArea(new Dimension(25,10)));
		panelDetails.add(labeImage);
		
		ToolBarShop toolBarShop = new ToolBarShop(null);
		
		JTabbedPane tabs = new JTabbedPane();
		
		productsByShop = new TableProductsByShop(null);
		tableOrders = new TableOrders(null);
		tabs.add("My Product List", productsByShop);
		tabs.add("My Orders", tableOrders);
		
		add(panelDetails);
		add(Box.createRigidArea(new Dimension(25,50)));
		add(toolBarShop);
		add(Box.createRigidArea(new Dimension(25,20)));
		add(tabs);
	}
	
	public void refreshProductsTable(ArrayList<Product> products) {
		productsByShop.refreshTable(products);
	}
	
//	public static void main(String[] args) {
//		MainWindowShop mainWindowShop = new MainWindowShop();
//		ArrayList<Product> products = new ArrayList<>();
//		products.add(new Product("uno", 23, "sadas"));
//		mainWindowShop.refreshProductsTable(products);
//		mainWindowShop.setVisible(true);
//	}
}