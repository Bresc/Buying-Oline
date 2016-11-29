package view.admin;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controller.Actions;
import controller.Controller;

public class ToolBar extends JToolBar{

	private static final long serialVersionUID = 1L;
	private JTextField txSearch;

	public ToolBar(Controller controller) {
		setBackground(Color.decode("#00BBBB"));
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setToolTipText("Add estudent");
		btnAddUser.setIcon(new ImageIcon(new ImageIcon("src/img/addUser.png").getImage().getScaledInstance(20, 20, 20)));
		btnAddUser.setActionCommand(Actions.SHOW_ADD_USER_DIALOG.toString());
		btnAddUser.setBackground(Color.decode("#FFFFFF"));
		btnAddUser.addActionListener(controller);
		add(btnAddUser);
		
		addSeparator();
		addSeparator();
		
		JButton btnAddShop = new JButton("Add Shop");
		btnAddShop.setToolTipText("Add Shop");
		btnAddShop.setActionCommand(Actions.SHOW_ADD_SHOP_DIALOG.toString());
		btnAddShop.addActionListener(controller);
		btnAddShop.setBackground(Color.decode("#FFFFFF"));
		add(btnAddShop);
		
		addSeparator();
		addSeparator();
		
		JButton btnAddProduct = new JButton("Add New Product");
		btnAddProduct.setToolTipText("Add Product");
		btnAddProduct.setBackground(Color.decode("#FFFFFF"));
		btnAddProduct.setActionCommand(Actions.SHOW_ADD_PRODUCT.toString());
		btnAddProduct.addActionListener(controller);
		add(btnAddProduct);
		
		addSeparator();
		addSeparator();
		
		txSearch = new JTextField();
		add(txSearch);
		
		addSeparator();
		addSeparator();
	
		JButton btnSearch = new JButton("Search");
		btnSearch.setToolTipText("Search");
		btnSearch.setBackground(Color.decode("#FFFFFF"));
		add(btnSearch);
		
		addSeparator();
		addSeparator();
		
		setFloatable(false);
	}
	
	public String getTextSearch(){
		return txSearch.getText();
	}
}