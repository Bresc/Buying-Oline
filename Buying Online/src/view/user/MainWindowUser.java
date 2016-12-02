package view.user;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controller.Controller;
import models.entities.Product;
import models.entities.Shop;

public class MainWindowUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private ToolBar toolBarUser;
	
	private JPanel panelActualCenter;
	private PanelProductsFromShop panelProductsFromShop;
	private PanelShop panelShop;

	public MainWindowUser(Controller controller) {
		setSize(380, 600);
		setLayout(new BorderLayout());
//		setUndecorated(true);
		setLocationRelativeTo(null);
		setTitle("RestaurantSoft Client v0.01");
		getContentPane().setBackground(ConstanstUIUser.BACKGROUND_COLOR_MAIN_WINDOW_USER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		toolBarUser = new ToolBar(controller);
		add(toolBarUser, BorderLayout.NORTH);

		panelActualCenter = new JPanel();
		panelProductsFromShop = new PanelProductsFromShop(controller);
		
		panelShop =  new PanelShop(controller);
		add(new JScrollPane(panelActualCenter), BorderLayout.CENTER);
	}
	
	public void refreshShopList(ArrayList<Shop> shops , Controller controller){
		panelActualCenter.removeAll();
		panelShop.refreshCardRestaurant(shops);
		panelActualCenter.add(panelShop);
		panelActualCenter.updateUI();
		panelActualCenter.repaint();
	}


	public void changeToProductsFromShopPanel(ArrayList<Product> productsFromShop) {
		panelActualCenter.removeAll();
		panelProductsFromShop.refreshProducts(productsFromShop);
		panelActualCenter.add(panelProductsFromShop);
		panelActualCenter.updateUI();
		panelActualCenter.repaint();
	}
}