package view.login;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import controller.GeneralActions;
import controller.GeneralController;

public class DialogChooseWhoYouAre extends JDialog {

	private static final long serialVersionUID = 1L;

	private JButton buttonUser;
	private JButton buttonShop;

	public DialogChooseWhoYouAre(GeneralController generalController) {
		setTitle(ConstantsUILogin.TITLE_DIALOG_LOGIN);
		setSize(358, 130);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource(ConstantsUILogin.IMG_ICON_LOGIN)).getImage());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		setLayout(new GridLayout(1, 2));

		buttonUser = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/1480496151_user.png"))
				.getImage().getScaledInstance(80, 80, 0)));
		buttonUser.addActionListener(generalController);
		buttonUser.setActionCommand(GeneralActions.USER_LOG_IN.toString());
		add(buttonUser);

		buttonShop = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/1480496235_shop.png"))
				.getImage().getScaledInstance(80, 80, 0)));
		buttonShop.addActionListener(generalController);
		buttonShop.setActionCommand(GeneralActions.SHOP_LOG_IN.toString());
		add(buttonShop);
	}
}