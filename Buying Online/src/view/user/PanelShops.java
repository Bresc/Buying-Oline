package view.user;

import java.awt.BorderLayout;
import java.awt.GridLayout;
//import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controller.ControllerAdmin;
import models.entities.Shop;

public class PanelShops extends JPanel {
	private static final long serialVersionUID = 1L;
	private JPanel PanelShops;

	public PanelShops() {
		setLayout(new BorderLayout());
		PanelShops = new JPanel(new GridLayout(3, 1, 5, 10));
		JScrollPane js = new JScrollPane(PanelShops);
		add(js, BorderLayout.CENTER);
	}

	public void agregaShop(Shop shop, ControllerAdmin controller) {
		PanelShops.revalidate();
	}

	public void borrarTodos() {
		PanelShops = new JPanel(new GridLayout(3, 1, 5, 10));
		revalidate();
	}
}
