package view.user;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import controller.ActionsAdmin;
import controller.ActionsUser;
import controller.ControllerUser;

public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private JTextField txSearch;
	private JMenu menuFile;

	public ToolBar( ControllerUser controllerUser) {
		setBackground(Color.decode("#FFC557"));
		GridSystem gridToolBar = new GridSystem(this);

		JMenuBar menu = new JMenuBar();
		menu.setBackground(Color.decode("#FFC557"));
		
		menuFile = new JMenu();
		menuFile.setIcon(new ImageIcon("src/img/Menu.png"));
		
		JMenuItem logOut = new JMenuItem("Log Out  ", new ImageIcon("src/img/Exit.png"));
		logOut.setBackground(Color.decode("#FFC557"));
		logOut.setActionCommand(ActionsAdmin.LOG_OUT.toString());
		logOut.addActionListener(controllerUser);
		menuFile.add(logOut);
		
		menu.add(menuFile);
		add(menu, gridToolBar.insertComponent(1, 0, 1, 0.1));

		txSearch = new JTextField();
		add(txSearch, gridToolBar.insertComponent(1, 2, 9, 0.1));

		JButton btnSearch = new JButton(new ImageIcon(getClass().getResource("/img/Search.png")));
		btnSearch.setBackground(Color.decode("#FFC557"));
		btnSearch.setBorder(null);
		btnSearch.setToolTipText("Search Restaurant");
		btnSearch.setActionCommand(ActionsUser.SEARCH_RESTAURANT.toString());
		btnSearch.addActionListener(controllerUser);
		add(btnSearch, gridToolBar.insertComponent(1, 11, 1, 0.1));

		setFloatable(false);
	}
}