package view.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Controller;
import models.entities.Product;

public class PanelProductsFromShop extends JPanel{

	
	private static final long serialVersionUID = 1L;
	Controller controller;
	private JPanel pnlMain;
	private GridBagConstraints gridManger;

	public PanelProductsFromShop(Controller controller) {
		this.controller = controller;
		setLayout(new BorderLayout());

		pnlMain = new JPanel(new GridBagLayout());
		gridManger = new GridBagConstraints();
		add(pnlMain, BorderLayout.CENTER);
	}

	public void refreshProducts(ArrayList<Product> productsFromShop) {
		pnlMain.removeAll();
		int columns = 0;
		int rows = 0;
		for (Product product : productsFromShop) {
			gridManger.gridx = columns;
			gridManger.gridy = rows;
			gridManger.weightx = 0.5;
			gridManger.weighty = 0.5;
			gridManger.gridheight = 1;
			gridManger.gridwidth = 1;
			gridManger.fill = GridBagConstraints.HORIZONTAL;
			columns++;
			if (columns == 1) {
				columns = 0;
				rows++;
			}

			JPanel pnlBigGroup = new JPanel(new BorderLayout());
			pnlBigGroup.setBackground(ConstanstUIUser.BACKGROUND_COLOR_MAIN_WINDOW_USER);
			pnlBigGroup.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

			// NORTH
			JLabel lbImage = new JLabel();
			Image img = new ImageIcon(product.getSrcImg()).getImage().getScaledInstance(320, 150,
					java.awt.Image.SCALE_AREA_AVERAGING);
			lbImage.setIcon(new ImageIcon(img));
			lbImage.setHorizontalAlignment((int) CENTER_ALIGNMENT);
			lbImage.setVerticalAlignment((int) CENTER_ALIGNMENT);
			lbImage.setPreferredSize(new Dimension(320, 150));
			pnlBigGroup.add(lbImage, BorderLayout.NORTH);

			// CENTER
			JPanel pnlCenter = new JPanel(new BorderLayout());
			pnlCenter.setBackground(Color.WHITE);

			JLabel lbNameProduct = new JLabel(product.getName());
			lbNameProduct.setFont(new Font("Arial", Font.BOLD, 16));
			lbNameProduct.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
			lbNameProduct.setVerticalAlignment(JLabel.NORTH);
			lbNameProduct.setPreferredSize(new Dimension(320, 30));
			pnlCenter.add(lbNameProduct, BorderLayout.NORTH);

			JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0));
			pnlButton.setBackground(Color.WHITE);
			pnlButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
			pnlCenter.add(pnlButton, BorderLayout.CENTER);

			pnlBigGroup.add(pnlCenter, BorderLayout.CENTER);
			pnlMain.add(pnlBigGroup, gridManger);
		}
		repaint();
		revalidate();
	}
}
