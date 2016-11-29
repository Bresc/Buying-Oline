package view.admin;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import controller.Controller;
import models.entities.Shop;
import models.entities.User;

public class MainWindowAdmin extends JFrame{

	private static final long serialVersionUID = 1L;
	private ToolBar toolbar;
	private TableUser tableUser;
	private TableShop tableShop;

	public MainWindowAdmin(Controller controller) {
		setLayout(new BorderLayout());
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("TutoringSoft Admin v0.1");
		setIconImage(new ImageIcon("src/img/icon.png").getImage());
		toolbar = new ToolBar(controller);
		add(toolbar, BorderLayout.PAGE_START);
		
		JTabbedPane objects = new JTabbedPane();
		
		tableUser = new TableUser(controller);
		tableShop = new TableShop(controller);
		
		objects.addTab("Shop" , tableShop);
		objects.addTab("User" , tableUser);
		add(objects);
		
		setVisible(true);
	}
	public void refreshTableShop(ArrayList<Shop> shops){
		tableShop.refreshTable(shops);
	}
	
	public void refreshTableUser(ArrayList<User> users){
		tableUser.refreshTable(users);
	}
	
	public void showMessageDialog(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}