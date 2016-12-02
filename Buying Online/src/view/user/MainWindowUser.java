package view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Actions;
import controller.Controller;
import models.entities.Product;
import models.entities.Shop;

public class MainWindowUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private ToolBar toolBarUser;
	
	private JPanel panelActualCenter;
	private PanelProductsFromShop panelProductsFromShop;
	private JButton btnFavorites;
	private JButton btnListProducts;
	private AbstractButton btnShoppingCar;
	private JButton btnSettings;
	private PanelShop PanelShop;

	public MainWindowUser(Controller controller) {
		setSize(380, 600);
		setLayout(new BorderLayout());
		setUndecorated(true);
		setLocationRelativeTo(null);
		setTitle("RestaurantSoft Client v0.01");
		getContentPane().setBackground(ConstanstUIUser.BACKGROUND_COLOR_MAIN_WINDOW_USER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		toolBarUser = new ToolBar(controller);
		add(toolBarUser, BorderLayout.NORTH);

//		panelActualCenter = new JPanel();
//		panelProductsFromShop = new PanelProductsFromShop(controller);
//		
		PanelShop =  new PanelShop(controller);
		PanelShop.setBackground(Color.white);
//		panelActualCenter.add(panelShops);
//		add(new JScrollPane(panelActualCenter), BorderLayout.CENTER);

		add(PanelShop, BorderLayout.CENTER);
		
		
		JPanel panelButtonsOptions = new JPanel();
		panelButtonsOptions.setLayout(new GridLayout(1,4));

		btnFavorites = new JButton();
		btnFavorites.addActionListener(controller);
		btnFavorites.setBorderPainted(false);
		btnFavorites.setBackground(Color.WHITE);
		btnFavorites.setIcon(new ImageIcon(new ImageIcon("src/img/Baker.png").getImage().getScaledInstance(40, -10, Image.SCALE_AREA_AVERAGING)));
		btnFavorites.setActionCommand(Actions.VIEW_USER_FAVORITES.toString());
		panelButtonsOptions.add(btnFavorites);

		btnListProducts = new JButton();
		btnListProducts.addActionListener(controller);
		btnListProducts.setBorderPainted(false);
		btnListProducts.setBackground(Color.WHITE);
		btnListProducts.setIcon(new ImageIcon(new ImageIcon("src/img/Owner.png").getImage().getScaledInstance(40, -10, Image.SCALE_AREA_AVERAGING)));
		btnListProducts.setActionCommand(Actions.OPT_USER_VIEW_PRODUCTS.toString());
		panelButtonsOptions.add(btnListProducts);

		btnShoppingCar = new JButton();
		btnShoppingCar.addActionListener(controller);
		btnShoppingCar.setBorderPainted(false);
		btnShoppingCar.setBackground(Color.WHITE);
		btnShoppingCar.setIcon(new ImageIcon(new ImageIcon("src/img/buyer.png").getImage().getScaledInstance(40, -10, Image.SCALE_AREA_AVERAGING)));
		btnShoppingCar.setActionCommand(Actions.SHOPPING_CAR_USER.toString());
		panelButtonsOptions.add(btnShoppingCar);
		
		btnSettings = new JButton();
		btnSettings.addActionListener(controller);
		btnSettings.setBorderPainted(false);
		btnSettings.setBackground(Color.WHITE);
		btnSettings.setIcon(new ImageIcon(new ImageIcon("src/img/Menu.png").getImage().getScaledInstance(40, -10, Image.SCALE_AREA_AVERAGING)));
		btnSettings.setActionCommand(Actions.USER_SETTINGS.toString());
		panelButtonsOptions.add(btnSettings);

		
		
		panelButtonsOptions.setOpaque(true);
		
		add(panelButtonsOptions, BorderLayout.SOUTH);

	}
	


	public void changeToProductsFromShopPanel(ArrayList<Product> productsFromShop) {
		panelActualCenter.removeAll();
		panelProductsFromShop.refreshProducts(productsFromShop);
		panelActualCenter.add(panelProductsFromShop);
		panelActualCenter.updateUI();
		panelActualCenter.repaint();
	}

}