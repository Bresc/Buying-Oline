package view.login;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.GeneralActions;
import controller.GeneralController;

public class PanelContainer extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JTextField psPassword;
	private JLabel labelPassword;
	private JLabel labelNickName;

	public PanelContainer(GeneralController generalController) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(ConstantsUILogin.COLOR_BACKGROUND_LOGIN);

		JButton btnImgCharacteristic = new JButton();
		btnImgCharacteristic.setBorder(null);
		btnImgCharacteristic.setAlignmentX(CENTER_ALIGNMENT);
		Image img = new ImageIcon(ConstantsUILogin.URL_IMG_WELCOME).getImage().getScaledInstance(200, 150,
				Image.SCALE_AREA_AVERAGING);
		btnImgCharacteristic.setIcon(new ImageIcon(img));
		add(btnImgCharacteristic);

		labelNickName = new JLabel(ConstantsUILogin.NICK_NAME);
		labelNickName.setAlignmentX(CENTER_ALIGNMENT);
		add(labelNickName);

		txtUserName = new JTextField();
		add(txtUserName);

		labelPassword = new JLabel(ConstantsUILogin.PASSWORD);
		labelPassword.setAlignmentX(CENTER_ALIGNMENT);
		add(labelPassword);

		psPassword = new JPasswordField();
		add(psPassword);

		JPanel pnButtons = new JPanel();
		pnButtons.setBackground(ConstantsUILogin.COLOR_BACKGROUND_LOGIN);
		pnButtons.setLayout(new FlowLayout());

		JButton btnLogin = new JButton(ConstantsUILogin.LOGIN);
		btnLogin.setBackground(ConstantsUILogin.COLOR_BACKGROUND_BTN);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.addActionListener(generalController);
		btnLogin.setActionCommand(GeneralActions.VALIDATE_USER_FROM_LOGIN.toString());
		pnButtons.add(btnLogin);

		JButton btnSignUp = new JButton(ConstantsUILogin.SIGN_UP);
		btnSignUp.addActionListener(generalController);
		btnSignUp.setBackground(ConstantsUILogin.COLOR_BACKGROUND_BTN);
		btnSignUp.setForeground(Color.WHITE);
		btnSignUp.setActionCommand(GeneralActions.SHOW_REGISTER_DIALOG.toString());
		pnButtons.add(btnSignUp);
		add(pnButtons);
	}

	public String getUsername() {
		return txtUserName.getText();
	}

	public String getName() {
		return txtUserName.getText();
	}

	public String getPassword() {
		return psPassword.getText();
	}

	public void clear() {
		txtUserName.setText("");
		psPassword.setText("");
		revalidate();
	}
}