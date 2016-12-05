package view.admin;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controller.Actions;
import controller.Controller;
import view.user.GridSystem;

public class ToolBar extends JToolBar{

	private static final long serialVersionUID = 1L;
	private JTextField txSearch;

	public ToolBar(Controller controller) {
		setBackground(Color.decode("#00BBBB"));
		GridSystem gridMenu = new GridSystem(this);
		
		JMenuBar menu = new JMenuBar();
		menu.setBackground(Color.decode("#00BBBB"));
		
		JMenu menuFile = new JMenu("File");
		
		JMenuItem logOut = new JMenuItem("Log Out  ", new ImageIcon("src/4.png"));
		logOut.setBackground(Color.decode("#00BBBB"));
		logOut.setActionCommand(Actions.LOG_OUT.toString());
		logOut.addActionListener(controller);
		menuFile.add(logOut);
		
		menu.add(menuFile);
		add(menu, gridMenu.insertComponent(0, 0, 1, 1));
		
		JButton btnAddUser = new JButton(new ImageIcon(getClass().getResource("/img/2.png")));
		btnAddUser.setToolTipText("Add User");
		btnAddUser.setActionCommand(Actions.SHOW_ADD_USER_DIALOG.toString());
		btnAddUser.setBackground(Color.decode("#FFFFFF"));
		btnAddUser.addActionListener(controller);
		add(btnAddUser, gridMenu.insertComponent(1, 0, 1, 0.1));
		
		JButton btnAddShop = new JButton(new ImageIcon(getClass().getResource("/img/1.png")));
		btnAddShop.setToolTipText("Add Shop");
		btnAddShop.setActionCommand(Actions.SHOW_ADD_SHOP_DIALOG.toString());
		btnAddShop.addActionListener(controller);
		btnAddShop.setBackground(Color.decode("#FFFFFF"));
		add(btnAddShop, gridMenu.insertComponent(1, 2, 1, 0.1));
		
		JButton btnAddProduct = new JButton(new ImageIcon(getClass().getResource("/img/2.png")));
		btnAddProduct.setToolTipText("Add Product");
		btnAddProduct.setBackground(Color.decode("#FFFFFF"));
		btnAddProduct.setActionCommand(Actions.SHOW_ADD_PRODUCT.toString());
		btnAddProduct.addActionListener(controller);
		add(btnAddProduct, gridMenu.insertComponent(1, 4, 1, 0.1));
		
		txSearch = new JTextField();
		add(txSearch, gridMenu.insertComponent(1, 6, 4, 0.1));
	
		JButton btnSearch = new JButton("Search");
		btnSearch.setToolTipText("Search");
		btnSearch.setBackground(Color.decode("#FFFFFF"));
		add(btnSearch, gridMenu.insertComponent(1, 10, 1, 0.1));
		
		addSeparator();
		addSeparator();
		
		setFloatable(false);
	}
	
	public String getTextSearch(){
		return txSearch.getText();
	}
}