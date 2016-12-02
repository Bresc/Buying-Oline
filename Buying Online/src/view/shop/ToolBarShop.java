package view.shop;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controller.Actions;
import controller.Controller;

public class ToolBarShop extends JToolBar{

	private static final long serialVersionUID = 1L;
	private JTextField txSearch;

	public ToolBarShop(Controller controller) {
		setBackground(Color.decode("#00BBBB"));
		
		JButton btnAddProduct = new JButton("Add product");
		btnAddProduct.setToolTipText("Add product");
		btnAddProduct.setIcon(new ImageIcon(new ImageIcon("src/img/default.png").getImage().getScaledInstance(20, 20, 20)));
		btnAddProduct.setActionCommand(Actions.SHOW_ADD_PRODUCT_FROM_SHOP_VIEW_DIALOG.toString());
		btnAddProduct.setBackground(Color.decode("#FFFFFF"));
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