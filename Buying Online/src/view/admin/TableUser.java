package view.admin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
import models.entities.User;

public class TableUser extends JPanel{

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMNS_NAME = {"Id", "Name", "Address"};
	private static final int ROW_HEIGHT = 25;
	private DefaultTableModel userModel;
	private JTable userTable;
	private JPopupMenu listUserMenu;

	public TableUser(Controller controller) {
		setLayout(new BorderLayout());
		
		listUserMenu = new JPopupMenu();

		JMenuItem itemDeleteUser = new JMenuItem("Delete User");
//		itemDeleteUser.setActionCommand(Actions.DELETE_USER.toString());
		itemDeleteUser.addActionListener(controller);
		listUserMenu.add(itemDeleteUser);
		
		JMenuItem itemEditUser = new JMenuItem("Edit User");
//		itemEditUser.setActionCommand(Actions.EDIT_USER.toString());
		itemEditUser.addActionListener(controller);
		listUserMenu.add(itemEditUser);


		userModel = new DefaultTableModel(COLUMNS_NAME, 0);

		userTable = new JTable(userModel){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column){ 
				return false; 
			}
		};
		userTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					showUserPopup(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		userTable.setRowHeight(ROW_HEIGHT);
		add(new JScrollPane(userTable), BorderLayout.CENTER);

	}

	private void showUserPopup(Component component, int x, int y) {
		listUserMenu.show(component, x, y);
	}

	public void addUser(User user) {
		userModel.addRow(user.toObjectVector());
	}
	
	public void editUserToTable(User user, int cod) {
		userModel.setValueAt(user.getName(), searchCodInTable(cod), 1);
	}
	
	public void refreshTable(ArrayList<User> users){
		userModel.setRowCount(0);
		for (User user : users) {
			userModel.addRow(user.toObjectVector());
		}
	}

	public int getUsertInTable(){
		return (int) (userTable.getValueAt(userTable.getSelectedRow(), 0));
	}
	
	public int searchCodInTable(int cod){
		int num = 0;
		for (int i = 0; i < userModel.getRowCount(); i++) {
			if (((int)userModel.getValueAt(i, 0)) == cod) {
				num = i;
			}
		}
		return num;
	}
}