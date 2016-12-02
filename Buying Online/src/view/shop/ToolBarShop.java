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
		
		JButton btnAddOrder = new JButton("New Order");
		btnAddOrder.setToolTipText("Add estudent");
		btnAddOrder.setIcon(new ImageIcon(new ImageIcon("src/img/default.png").getImage().getScaledInstance(20, 20, 20)));
		btnAddOrder.setActionCommand(Actions.SHOW_ADD_ORDER_DIALOG.toString());
		btnAddOrder.setBackground(Color.decode("#FFFFFF"));
		btnAddOrder.addActionListener(controller);
		add(btnAddOrder);
		
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