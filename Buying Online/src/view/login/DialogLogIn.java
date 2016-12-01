package view.login;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Actions;
import controller.Controller;

public class DialogLogIn extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textToWrite;
	private JTextField password;
	private JLabel labelPassWord;
	private JLabel labelNickName;
	private JButton buttonAgreeAdmin;
	private JButton buttonAgreeUser;
	private JButton buttonAgreeShop;
	
	public DialogLogIn(Controller controller) {
		setTitle("Log In");
		setIconImage(new ImageIcon(getClass().getResource("/img/1480497089_vector_65_12.png")).getImage());
		setSize(150, 150);
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		labelNickName = new JLabel("Name");
		add(labelNickName);
		
		textToWrite = new JTextField();
		textToWrite.addKeyListener(controller);
		add(textToWrite);
		
		labelPassWord = new JLabel("Password");
		add(labelPassWord);
		
		password = new JPasswordField();
		password.addKeyListener(controller);
		add(password);
		
		buttonAgreeAdmin = new JButton("Log In");
		buttonAgreeAdmin.addActionListener(controller);
		buttonAgreeAdmin.setActionCommand(Actions.ADMIN_VIEW.toString());
//		buttonAgreeAdmin.setEnabled(false);
		buttonAgreeAdmin.setVisible(false);
		add(buttonAgreeAdmin);
		
		buttonAgreeUser = new JButton("Log In");
		buttonAgreeUser.addActionListener(controller);
		buttonAgreeUser.setActionCommand(Actions.USER_VIEW.toString());
		buttonAgreeUser.setEnabled(true);
		buttonAgreeUser.setVisible(false);
		add(buttonAgreeUser);
		
		buttonAgreeShop = new JButton("Log In");
		buttonAgreeShop.addActionListener(controller);
		buttonAgreeShop.setActionCommand(Actions.SHOP_VIEW.toString());
//		buttonAgreeShop.setEnabled(false);
		buttonAgreeShop.setVisible(false);
		add(buttonAgreeShop);
	}

	public String getTheName(){
		return textToWrite.getText();
	}
	
	public String getPassword(){
		return password.getText();
	}
	
	public void changeTheButtonUser(boolean confirmation){
		if (confirmation) {
			buttonAgreeUser.setEnabled(true);
		}
	}
	
	public void changeTheButtonAdmin(boolean confirmation){
		if (confirmation == true) {
			buttonAgreeAdmin.setEnabled(true);
		}
	}
	
	public void adminView() {
		buttonAgreeAdmin.setVisible(true);
		setVisible(true);
	}
	
	public void UserView(){
		buttonAgreeUser.setVisible(true);
		setVisible(true);
	}
	
	public void CompanyView(){
		buttonAgreeShop.setVisible(true);
		setVisible(true);
	}
}