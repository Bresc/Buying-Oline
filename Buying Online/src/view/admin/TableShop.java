package view.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import models.entities.Shop;

public class TableShop extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMNS_NAME = { "id", "Name" };
	private static final int ROW_HEIGHT = 25;
	private DefaultTableModel shopModel;
	private JTable shopTable;
	private JPopupMenu listshopMenu;

	public TableShop(Controller controller) {
		setLayout(new BorderLayout());

		listshopMenu = new JPopupMenu();

		JMenuItem itemDeleteShop = new JMenuItem("Delete Shop");
		// itemDeleteShop.setActionCommand(Actions.DELETE_TUTOR.toString());
		itemDeleteShop.addActionListener(controller);
		listshopMenu.add(itemDeleteShop);

		JMenuItem itemEditShop = new JMenuItem("Edit Shop");
		// itemEditShop.setActionCommand(Actions.EDIT_TUTOR.toString());
		itemEditShop.addActionListener(controller);
		listshopMenu.add(itemEditShop);

		shopModel = new DefaultTableModel(COLUMNS_NAME, 0);

		shopTable = new JTable(shopModel) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		shopTable.getTableHeader().setBackground(Color.decode("#E6E6FA"));
		shopTable.getTableHeader().setFont(new Font("Ahaori", Font.BOLD, 18));
		shopTable.getTableHeader().setForeground(Color.decode("#1E90FF"));
		shopTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					showShopPopup(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		shopTable.setRowHeight(ROW_HEIGHT);
		add(new JScrollPane(shopTable), BorderLayout.CENTER);

	}

	private void showShopPopup(Component component, int x, int y) {
		listshopMenu.show(component, x, y);
	}

	public void addShopToTable(Shop shop) {
		shopModel.addRow(shop.toObjectVector());
	}

	public void editShopToTable(Shop shop, int id) {
		shopModel.setValueAt(shop.getName(), searchNameInTable(id), 2);
	}

	public void refreshTable(ArrayList<Shop> shops) {
		shopModel.setRowCount(0);
		;
		for (Shop shop : shops) {
			addShopToTable(shop);
		}
	}

	public int getShopInTable() {
		return (int) (shopTable.getValueAt(shopTable.getSelectedRow(), 0));
	}

	public int searchNameInTable(int id) {
		for (int i = 0; i < shopTable.getRowCount(); i++) {
			if (((int) shopTable.getValueAt(i, 0)) == id) {
				return i;
			}
		}
		return -1;
	}
}