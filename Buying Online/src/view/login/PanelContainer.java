package view.login;

import java.awt.Color;

import javax.swing.BoxLayout;
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
	private JButton btnLogin;

	public PanelContainer(GeneralController generalController) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.decode("#4383BD"));

		labelNickName = new JLabel("Name");
		labelNickName.setForeground(Color.WHITE);
		add(labelNickName);

		txtUserName = new JTextField();
		add(txtUserName);

		labelPassword = new JLabel("Password");
		labelPassword.setForeground(Color.WHITE);
		add(labelPassword);

		psPassword = new JPasswordField();
		add(psPassword);

		btnLogin = new JButton("Log In");
		btnLogin.addActionListener(generalController);
		btnLogin.setActionCommand(GeneralActions.VALIDATE_USER_FROM_LOGIN.toString());
		add(btnLogin);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(generalController);
		btnSignUp.setActionCommand(GeneralActions.SHOW_REGISTER_DIALOG.toString());
		add(btnSignUp);
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
	}
}