package view.user;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import controller.Actions;
import controller.Controller;

public class ToolBar extends JToolBar {

	private static final long serialVersionUID = 1L;
	private JTextField txSearch;

	public ToolBar(Controller controller) {
		setBackground(Color.decode("#FFC557"));
		GridSystem gridToolBar = new GridSystem(this);

		JButton btnShowDropDownMenu = new JButton(new ImageIcon(getClass().getResource("/img/Menu.png")));
		btnShowDropDownMenu.setBackground(Color.decode("#FFC557"));
		btnShowDropDownMenu.setBorder(null);
		btnShowDropDownMenu.setToolTipText("Show Drop Down Menu");
		btnShowDropDownMenu.setActionCommand(Actions.SHOW_DROP_DOWN_MENU.toString());
		btnShowDropDownMenu.addActionListener(controller);
		add(btnShowDropDownMenu, gridToolBar.insertComponent(1, 0, 1, 0.1));

		txSearch = new JTextField();
		add(txSearch, gridToolBar.insertComponent(1, 2, 9, 0.1));

		JButton btnSearch = new JButton(new ImageIcon(getClass().getResource("/img/Search.png")));
		btnSearch.setBackground(Color.decode("#FFC557"));
		btnSearch.setBorder(null);
		btnSearch.setToolTipText("Search Restaurant");
		btnSearch.setActionCommand(Actions.SEARCH_RESTAURANT.toString());
		btnSearch.addActionListener(controller);
		add(btnSearch, gridToolBar.insertComponent(1, 11, 1, 0.1));

		setFloatable(false);
	}
}