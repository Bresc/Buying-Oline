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
		getContentPane().setBackground(Color.decode("#4383BD"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(Color.decode("#4383BD"));
		labelBuyingOnline = new JLabel("Buying Online");
		labelBuyingOnline.setForeground(Color.WHITE);
		panelTitle.add(labelBuyingOnline);

		add(panelTitle);

		panelConteiner = new PanelContainer(generalController);
		add(panelConteiner);

		setVisible(true);
	}

	public String getTheName() {
		return panelConteiner.getName();
	}

	public String getPassword() {
		return panelConteiner.getPassword();
	}

	public void clearLoginDialog() {
		panelConteiner.clear();
	}
}