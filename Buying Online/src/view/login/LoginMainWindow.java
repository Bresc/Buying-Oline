package view.login;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.GeneralController;

public class LoginMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelBuyingOnline;
	private PanelContainer panelConteiner;

	public LoginMainWindow(GeneralController generalController) {
		setTitle(ConstantsUILogin.TITLE_DIALOG_LOGIN);
		setIconImage(new ImageIcon(getClass().getResource(ConstantsUILogin.IMG_ICON_LOGIN)).getImage());
		setSize(320, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(ConstantsUILogin.COLOR_BACKGROUND_LOGIN);
		labelBuyingOnline = new JLabel(ConstantsUILogin.INITIAL_MESSAGE);
		labelBuyingOnline.setFont(ConstantsUILogin.FONT_TITLE);
		panelTitle.add(labelBuyingOnline);

		add(panelTitle);

		panelConteiner = new PanelContainer(generalController);
		add(panelConteiner);
		setVisible(true);
	}

	public String getUsername() {
		return panelConteiner.getUsername();
	}

	public String getPassword() {
		return panelConteiner.getPassword();
	}

	public void clearLoginDialog() {
		panelConteiner.clear();
	}
}