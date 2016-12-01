package view.login;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import controller.Actions;
import controller.Controller;

public class DialogChooseWhoYouAre extends JDialog{

	private static final long serialVersionUID = 1L;

	private JButton buttonAdmin;
	private JButton buttonUser;
	private JButton buttonShop;
	
	public DialogChooseWhoYouAre(Controller controller) {
		setTitle("Log in");
		setSize(358, 130);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("/img/1480497089_vector_65_12.png")).getImage());
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		
		buttonAdmin = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/1480496207_administrator.png")).getImage().getScaledInstance(80, 80, 0)));
		buttonAdmin.addActionListener(controller);
		buttonAdmin.setActionCommand(Actions.OPEN_DIALOG_LOG_IN.toString());
		add(buttonAdmin);
		
		buttonUser = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/1480496151_user.png")).getImage().getScaledInstance(80, 80, 0)));
		buttonUser.addActionListener(controller);
		buttonUser.setActionCommand(Actions.USER_LOG_IN.toString());
		add(buttonUser);
		
		buttonShop = new JButton(new ImageIcon(new ImageIcon(getClass().getResource("/img/1480496235_shop.png")).getImage().getScaledInstance(80, 80, 0)));
		buttonShop.addActionListener(controller);
		buttonShop.setActionCommand(Actions.SHOP_LOG_IN.toString());
		add(buttonShop);
	}
}