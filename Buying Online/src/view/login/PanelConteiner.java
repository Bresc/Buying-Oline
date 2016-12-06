package view.login;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.ActionsShop;
import controller.ControllerShop;

public class PanelConteiner extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textToWrite;
	private JTextField password;
	private JLabel labelPassWord;
	private JLabel labelNickName;
	private JButton buttonAgreeLogIn;

	public PanelConteiner( ControllerShop controllerShop) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.decode("#4383BD"));

		labelNickName = new JLabel("Name");
		labelNickName.setForeground(Color.WHITE);
		add(labelNickName);

		textToWrite = new JTextField();
		add(textToWrite);

		labelPassWord = new JLabel("Password");
		labelPassWord.setForeground(Color.WHITE);
		add(labelPassWord);

		password = new JPasswordField();
		add(password);

		buttonAgreeLogIn = new JButton("Log In");
		buttonAgreeLogIn.addActionListener(controllerShop);
		buttonAgreeLogIn.setActionCommand(ActionsShop.CONFIRM.toString());
		buttonAgreeLogIn.setVisible(true);
		add(buttonAgreeLogIn);

		JButton buttonRegister = new JButton("Sign Up");
		buttonRegister.addActionListener(controllerShop);
		buttonRegister.setActionCommand(ActionsShop.OPEN_DIALOG_CHOOSE.toString());
		buttonRegister.setVisible(true);
		add(buttonRegister);
	}

	public String getTheName() {
		return textToWrite.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public void clear() {
		textToWrite.setText("");
		password.setText("");
	}
}