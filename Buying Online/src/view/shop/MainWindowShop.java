package view.shop;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainWindowShop extends JFrame{

	
	private static final long serialVersionUID = 1L;

	public MainWindowShop() {
		setTitle("Shop");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.white);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setSize(450, 450);
		
		JPanel panelDetails = new JPanel();
		panelDetails.setLayout(new BoxLayout(panelDetails, BoxLayout.X_AXIS));
		panelDetails.setBackground(Color.white);
		JLabel labelName = new JLabel("SUBWAY");
		labelName.setFont(new Font("Segoe UI Black", Font.ITALIC, 59));
		labelName.setForeground(Color.decode("#6699CC"));
		JLabel labeImage = new JLabel();
		labeImage.setIcon(new ImageIcon(new ImageIcon("src/img/Owner.png").getImage().getScaledInstance(150, 150, 150)));
		
		panelDetails.add(labelName);
		panelDetails.add(Box.createRigidArea(new Dimension(25,10)));
		panelDetails.add(labeImage);
		
		
		ToolBarShop toolBarShop = new ToolBarShop(null);
//		toolBarShop.setMaximumSize(new Dimension(this.getWidth(), 200));
		
		JTabbedPane tabs = new JTabbedPane();
		
		TableProductsByShop productsByShop = new TableProductsByShop(null);
		TableOrders tableOrders = new TableOrders(null);
		tabs.add("My Product List", productsByShop);
		tabs.add("My Orders", tableOrders);
//		tabs.add("My Product List", component)
		
		
		add(panelDetails);
		add(Box.createRigidArea(new Dimension(25,50)));
		add(toolBarShop);
		add(Box.createRigidArea(new Dimension(25,20)));
		add(tabs);
		
		
		
				
	}
	
	public static void main(String[] args) {
		MainWindowShop mainWindowShop = new MainWindowShop();
		mainWindowShop.setVisible(true);
	}
}