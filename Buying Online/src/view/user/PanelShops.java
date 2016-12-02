package view.user;

import java.awt.BorderLayout;
import java.awt.GridLayout;
//import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import controller.Controller;
import models.entities.Shop;

public class PanelShops extends JPanel {
	private static final long serialVersionUID = 1L;
	
//	private ArrayList<PanelShop> listaShops;
	JPanel PanelShops;
	
	public PanelShops() {
		setLayout(new BorderLayout());
		PanelShops = new JPanel(new GridLayout(3,1, 5, 10));
		JScrollPane js = new JScrollPane(PanelShops);
//		listaShops = new ArrayList<>();
		add(js, BorderLayout.CENTER);
	}
	
	
	public void agregaShop(Shop shop, Controller controller) {
		//PanelShop pnshop = new PanelShop(shop, controller);
		//listaShops.add(pnshop);
		//listaShops.add(pnshop);
		PanelShops.revalidate();
	}
	
	public void borrarTodos(){
		PanelShops = new JPanel(new GridLayout(3, 1, 5, 10));
		revalidate();
	}

}
