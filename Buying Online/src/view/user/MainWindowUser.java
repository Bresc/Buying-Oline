package view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controller.ActionsUser;
import controller.ControllerUser;
import models.entities.Product;
import models.entities.Shop;

public class MainWindowUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private ToolBar toolBarUser;
	
	private JPanel panelActualCenter;
	private PanelProductsFromShop panelProductsFromShop;
	private PanelShop panelShop;
	private JButton btnSettings;
	private JButton btnShoppingCar;
	private JButton btnListProducts;
	private JButton btnFavorites;

	public MainWindowUser( ControllerUser controllerUser) {
		setSize(380, 600);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setTitle(ConstanstUIUser.TITLE_MAIN_WINDOW_USER);
		getContentPane().setBackground(ConstanstUIUser.BACKGROUND_COLOR_MAIN_WINDOW_USER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		toolBarUser = new ToolBar(controllerUser);
		add(toolBarUser, BorderLayout.NORTH);

		panelActualCenter = new JPanel();
		panelProductsFromShop = new PanelProductsFromShop(controllerUser);
		
		panelShop =  new PanelShop(controllerUser);
		add(new JScrollPane(panelActualCenter), BorderLayout.CENTER);
		
		JPanel panelButtonsOptions = new JPanel();
		panelButtonsOptions.setLayout(new GridLayout(1,4));

		btnFavorites = new JButton();
		btnFavorites.addActionListener(controllerUser);
		btnFavorites.setBorderPainted(false);
		btnFavorites.setBackground(Color.WHITE);
		btnFavorites.setIcon(new ImageIcon(ConstanstUIUser.IMAGE_FAVORITES.getImage().getScaledInstance(40, -10, Image.SCALE_AREA_AVERAGING)));
		btnFavorites.setActionCommand(ActionsUser.VIEW_USER_FAVORITES.toString());
		panelButtonsOptions.add(btnFavorites);

		btnListProducts = new JButton();
		btnListProducts.addActionListener(controllerUser);
		btnListProducts.setBorderPainted(false);
		btnListProducts.setBackground(Color.WHITE);
		btnListProducts.setIcon(new ImageIcon(ConstanstUIUser.IMAGE_ALL_TO_MENU_USER.getImage().getScaledInstance(40, -10, Image.SCALE_AREA_AVERAGING)));
		btnListProducts.setActionCommand(ActionsUser.OPT_USER_VIEW_PRODUCTS.toString());
		panelButtonsOptions.add(btnListProducts);

		btnShoppingCar = new JButton();
		btnShoppingCar.addActionListener(controllerUser);
		btnShoppingCar.setBorderPainted(false);
		btnShoppingCar.setBackground(Color.WHITE);
		btnShoppingCar.setIcon(new ImageIcon(ConstanstUIUser.IMAGE_CAR_BUY.getImage().getScaledInstance(40, -10, Image.SCALE_AREA_AVERAGING)));
		btnShoppingCar.setActionCommand(ActionsUser.SHOPPING_CAR_USER.toString());
		panelButtonsOptions.add(btnShoppingCar);
		
		btnSettings = new JButton();
		btnSettings.addActionListener(controllerUser);
		btnSettings.setBorderPainted(false);
		btnSettings.setBackground(Color.WHITE);
		btnSettings.setIcon(new ImageIcon(ConstanstUIUser.IMAGE_SETTINGS.getImage().getScaledInstance(40, -10, Image.SCALE_AREA_AVERAGING)));
		btnSettings.setActionCommand(ActionsUser.USER_SETTINGS.toString());
		panelButtonsOptions.add(btnSettings);

		
		
		panelButtonsOptions.setOpaque(true);
		
		add(panelButtonsOptions, BorderLayout.SOUTH);

	}
	
	public void refreshShopList(ArrayList<Shop> shops , ControllerUser controllerUser){
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