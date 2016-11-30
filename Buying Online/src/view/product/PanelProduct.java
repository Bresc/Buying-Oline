package view.product;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelProduct extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelProduct() {
		setLayout(new BorderLayout());
		init();
	}

	private void init() {
		JLabel labelImageProduct = new JLabel();
		this.add(labelImageProduct, BorderLayout.CENTER);

		JPanel panelDescription = new JPanel();
		panelDescription.setLayout(new FlowLayout());

		JPanel panelDetails = new JPanel();
		panelDetails.setLayout(new BoxLayout(panelDescription, BoxLayout.Y_AXIS));
		JLabel labelNameProduct = new JLabel();
		panelDetails.add(labelNameProduct);
		JLabel labelValueProduct = new JLabel();
		panelDetails.add(labelValueProduct);

		JButton buttonAdd = new JButton("ADD");
		panelDetails.add(buttonAdd);
		panelDescription.add(panelDetails);

		this.add(panelDescription, BorderLayout.SOUTH);
	}

}