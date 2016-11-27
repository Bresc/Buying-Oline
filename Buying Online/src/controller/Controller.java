package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.admin.MainWindowAdmin;
import view.user.MainWindowUser;

public class Controller implements ActionListener {
	
	private MainWindowUser mainWindowUser;
	private MainWindowAdmin mainWindowAdmin;

	public Controller() {
		mainWindowAdmin = new MainWindowAdmin(this);
		mainWindowUser = new MainWindowUser(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (Actions.valueOf(e.getActionCommand())) {
		case ADD_USER:
			break;
		}
	}
}