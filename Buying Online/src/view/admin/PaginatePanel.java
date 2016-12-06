package view.admin;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControllerAdmin;
import controller.ActionsAdmin;

public class PaginatePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel lbPage;

	public PaginatePanel(ControllerAdmin controller) {
		JButton btnLeft = new JButton(new ImageIcon("src/img/arrow_left.png"));
		btnLeft.setToolTipText("Go to left page");
		btnLeft.setActionCommand(ActionsAdmin.GO_LEFT_ARROW.toString());
		btnLeft.addActionListener(controller);
		btnLeft.setFocusPainted(false);
		btnLeft.setBorder(null);
		add(btnLeft);
		
		lbPage = new JLabel("Page: 0/0");
		lbPage.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(lbPage);
		
		JButton btnRight = new JButton(new ImageIcon("src/img/arrow_right.png"));
		btnRight.setToolTipText("Go to right page");
		btnRight.setActionCommand(ActionsAdmin.GO_RIGHT_ARROW.toString());
		btnRight.addActionListener(controller);
		btnRight.setFocusPainted(false);
		btnRight.setBorder(null);
		add(btnRight);
	}
	
	public void refreshPage(int actualPage, int maxPage){
		if (maxPage == 0) {
			maxPage = 1;
		}
		lbPage.setText("Page: "+ actualPage +"/" + maxPage);
	}
}