package view.login;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Actions;
import controller.Controller;

public class DialogLogIn extends JFrame{

	private static final long serialVersionUID = 1L;

	private JLabel labelBuyingOnline;
	private JLabel labelNotRegister;
	private JButton buttonAgreeLogIn;
	private PanelConteiner panelConteiner;
	
	public DialogLogIn(Controller controller) {
		setTitle("Log In");
		setIconImage(new ImageIcon(getClass().getResource("/img/1480497089_vector_65_12.png")).getImage());
		setSize(350, 200);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.decode("#4383BD"));
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() { 
		    @Override public void windowClosed(WindowEvent e) { 
		      System.exit(0);
		    }
		  });
		
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
//		setLayout(new BorderLayout());
		
		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(Color.decode("#4383BD"));
		labelBuyingOnline = new JLabel("Buying Online");
		labelBuyingOnline.setForeground(Color.WHITE);
		panelTitle.add(labelBuyingOnline);
		
		add(panelTitle);
		
		panelConteiner = new PanelConteiner(controller);		
		add(panelConteiner);
		
		JPanel panelRegister = new JPanel();
		panelRegister.setBackground(Color.decode("#4383BD"));
		labelNotRegister = new JLabel("are you registered, arent you? click here");
		labelNotRegister.setForeground(Color.WHITE);
		panelRegister.add(labelNotRegister);
		add(panelRegister);
	}

	public String getTheName(){
		return panelConteiner.getTheName();
	}
	
	public String getPassword(){
		return panelConteiner.getPassword();
	}
	
	public void changeTheButtonAdmin(boolean confirmation){
		if (confirmation == true) {
			buttonAgreeLogIn.setEnabled(true);
		}
	}
}