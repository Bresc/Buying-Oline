package view.admin;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.Actions;
import controller.Controller;


public class ToolBar extends JToolBar{

	private static final long serialVersionUID = 1L;

	public ToolBar(Controller controller) {
		
		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setToolTipText("Add estudent");
		btnAddUser.setIcon(new ImageIcon(new ImageIcon("src/img/addUser.png").getImage().getScaledInstance(20, 20, 20)));
		btnAddUser.setActionCommand(Actions.SHOW_ADD_USER_DIALOG.toString());
		btnAddUser.addActionListener(controller);
		add(btnAddUser);
		
		JButton btnAddShop = new JButton("Add Shop");
		btnAddShop.setToolTipText("Add Shop");
		btnAddShop.setActionCommand(Actions.SHOW_ADD_SHOP_DIALOG.toString());
		btnAddShop.addActionListener(controller);
		add(btnAddShop);
		
		JButton btnAddProduct = new JButton("Add New Product");
		btnAddProduct.setToolTipText("Add Product");
		btnAddProduct.setActionCommand(Actions.SHOW_ADD_PRODUCT.toString());
		btnAddProduct.addActionListener(controller);
		add(btnAddProduct);
			
		setFloatable(false);
	}
}