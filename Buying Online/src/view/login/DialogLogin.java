package view.login;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.GeneralController;

public class DialogLogin extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelBuyingOnline;
	private PanelContainer panelConteiner;

	public DialogLogin(GeneralController generalController) {
		setTitle("Log In");
		setIconImage(new ImageIcon(getClass().getResource("/img/1480497089_vector_65_12.png")).getImage());
		setSize(350, 200);
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