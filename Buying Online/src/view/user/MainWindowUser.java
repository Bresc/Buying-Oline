package view.user;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import controller.Controller;

public class MainWindowUser extends JFrame {

	private static final long serialVersionUID = 1L;
	private ToolBar toolBarUser;
	private PanelForRestaurants PanelForRestaurants;

	public MainWindowUser(Controller controller) {
		setSize(300, 450);
		setLayout(new BorderLayout());
		setUndecorated(true);
		setTitle("RestaurantSoft Client v0.01");
		getContentPane().setBackground(Color.decode("#FFC557"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		toolBarUser = new ToolBar(controller);
		add(toolBarUser, BorderLayout.NORTH);

		PanelForRestaurants = new PanelForRestaurants(controller);
		add(new JScrollPane(PanelForRestaurants), BorderLayout.CENTER);

		setVisible(true);
	}

	public void refreshCardRestaurant(Controller controller) {
		PanelForRestaurants.refreshCardRestaurant(controller);
	}
}