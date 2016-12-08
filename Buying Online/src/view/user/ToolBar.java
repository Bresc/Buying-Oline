package view.user;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import controller.ActionsAdmin;
import controller.ControllerUser;

public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private JMenu menuFile;

	public ToolBar(ControllerUser controllerUser) {
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

		setFloatable(false);
	}
}