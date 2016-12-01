package view.shop;

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
import models.entities.Order;
import models.entities.OrderProduct;

public class TableOrders extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = {"Id Order", "Product", "N° Products", "User", "State"};
	private static final int ROW_HEIGHT = 25;
	private DefaultTableModel modelOrders;
	private JTable tableOrders;
	private JPopupMenu listPopUp;

	public TableOrders(Controller controller) {
		setLayout(new BorderLayout());

		listPopUp = new JPopupMenu();

		JMenuItem jmiDeleteProduct = new JMenuItem("Delete Order");
		jmiDeleteProduct.addActionListener(controller);
		listPopUp.add(jmiDeleteProduct);

		JMenuItem itemEditShop = new JMenuItem("Change State");
		itemEditShop.addActionListener(controller);
		listPopUp.add(itemEditShop);

		modelOrders = new DefaultTableModel(COLUMN_NAMES, 0);

		tableOrders = new JTable(modelOrders) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableOrders.getTableHeader().setBackground(Color.decode("#E6E6FA"));
		tableOrders.getTableHeader().setFont(new Font("Ahaori", Font.BOLD, 18));
		tableOrders.getTableHeader().setForeground(Color.decode("#1E90FF"));
		tableOrders.setBackground(Color.blue);
		tableOrders.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					showOrderPopup(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		tableOrders.setRowHeight(ROW_HEIGHT);
		add(new JScrollPane(tableOrders), BorderLayout.CENTER);

	}

	private void showOrderPopup(Component component, int x, int y) {
		listPopUp.show(component, x, y);
	}

	public void addOrderToTable(Order order) {
		for (OrderProduct orderP : order.getProducts()) {
			Object[] obj = new Object[] {order.getId(), orderP.getProduct().getName(), orderP.getQuantity(), order.getUser().getName(), order.getState()};
			modelOrders.addRow(obj);					
		}
	}

	public void refreshTable(ArrayList<Order> listOrder) {
		modelOrders.setRowCount(0);
		
		for (Order order : listOrder) {
				addOrderToTable(order);		
		}
	}

	public int getOrderFromTable() {
		return (int) (tableOrders.getValueAt(tableOrders.getSelectedRow(), 0));
	}

	public int searchByNameInTable(int id) {
		for (int i = 0; i < tableOrders.getRowCount(); i++) {
			if (((int) tableOrders.getValueAt(i, 0)) == id) {
				return i;
			}
		}
		return -1;
	}
}