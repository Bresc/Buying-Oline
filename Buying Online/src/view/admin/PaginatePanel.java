package view.admin;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.Controller;
import controller.Actions;

public class PaginatePanel extends JPanel{

	private static final long serialVersionUID = 1L;

	public PaginatePanel(Controller controller) {
		JButton btnLeft = new JButton(new ImageIcon("src/img/arrow_left.png"));
		btnLeft.setToolTipText("Go to left page");
		btnLeft.setActionCommand(Actions.GO_LEFT_ARROW.toString());
		btnLeft.addActionListener(controller);
		btnLeft.setFocusPainted(false);
		btnLeft.setBorder(null);
		add(btnLeft);
		
		JButton btnRight = new JButton(new ImageIcon("src/img/arrow_right.png"));
		btnRight.setToolTipText("Go to right page");
		btnRight.setActionCommand(Actions.GO_RIGHT_ARROW.toString());
		btnRight.addActionListener(controller);
		btnRight.setFocusPainted(false);
		btnRight.setBorder(null);
		add(btnRight);
	}
}