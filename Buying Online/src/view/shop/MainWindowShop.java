package view.shop;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import controller.Actions;
import controller.Controller;
import models.entities.Product;

public class MainWindowShop extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private TableProductsByShop productsByShop;
	private TableOrders tableOrders;

	public MainWindowShop(Controller controller) {
		setTitle("Shop");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(450, 450);
		setLocationRelativeTo(null);
		
		JPanel pnlDetails = new JPanel();
		pnlDetails.setLayout(new BorderLayout());
		JMenuBar menu = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");
		
		JMenuItem logOut = new JMenuItem("Log Out  ", new ImageIcon("src/img/Exit.png"));
		logOut.setActionCommand(Actions.LOG_OUT.toString());
		logOut.addActionListener(controller);
		menuFile.add(logOut);
		menu.add(menuFile);
		
		pnlDetails.add(menu);
		
		ToolBarShop toolBarShop = new ToolBarShop(controller);
		
		JTabbedPane tabs = new JTabbedPane();
		
		productsByShop = new TableProductsByShop(null);
		tableOrders = new TableOrders(null);
		tabs.add("My Product List", productsByShop);
		tabs.add("My Orders", tableOrders);
		
		add(pnlDetails);
		add(toolBarShop);
		add(Box.createRigidArea(new Dimension(25,20)));
		add(tabs);
	}
	
	public void refreshProductsTable(ArrayList<Product> products) {
		productsByShop.refreshTable(products);
	}
}