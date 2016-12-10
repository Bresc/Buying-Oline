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

import controller.ActionsAdmin;
import controller.ControllerAdmin;
import models.entities.Product;
import models.entities.Shop;

public class TableProducts extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_NAMES = { "id", "Name", "Value" };
	private static final int ROW_HEIGHT = 25;
	private DefaultTableModel modelProducts;
	private JTable productsTable;
	private JPopupMenu listshopMenu;

	public TableProducts(ControllerAdmin controller) {
		setLayout(new BorderLayout());

		listshopMenu = new JPopupMenu();

		JMenuItem jmiDeleteProduct = new JMenuItem("Delete Product");
		jmiDeleteProduct.addActionListener(controller);
		jmiDeleteProduct.setActionCommand(ActionsAdmin.DELETE_PRODUCT.toString());
		listshopMenu.add(jmiDeleteProduct);

		JMenuItem itemEditShop = new JMenuItem("Edit Product");
		itemEditShop.addActionListener(controller);
		itemEditShop.setActionCommand(ActionsAdmin.SHOW_EDIT_PRODUCT.toString());
		listshopMenu.add(itemEditShop);

		modelProducts = new DefaultTableModel(COLUMN_NAMES, 0);

		productsTable = new JTable(modelProducts) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		productsTable.getTableHeader().setBackground(ConstantUIAdmin.COLOR_BACKGROUND_LOGIN);
		productsTable.getTableHeader().setFont(new Font("Ahaori", Font.BOLD, 18));
		productsTable.getTableHeader().setForeground(ConstantUIAdmin.COLOR_BACKGROUND_BTN);
		productsTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					showProductsPopup(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		productsTable.setRowHeight(ROW_HEIGHT);
		add(new JScrollPane(productsTable), BorderLayout.CENTER);

	}

	private void showProductsPopup(Component component, int x, int y) {
		listshopMenu.show(component, x, y);
	}

	public void addProductToTable(Product product) {
		modelProducts.addRow(product.toObjectVector());
	}

	public void editShopToTable(Shop shop, int id) {
		modelProducts.setValueAt(shop.getName(), searchByNameInTable(id), 2);
	}

	public void refreshTable(ArrayList<Product> listProducts) {
		modelProducts.setRowCount(0);
		for (Product product : listProducts) {
			addProductToTable(product);
		}
	}

	public int getProductFromTable() {
		return (int) (productsTable.getValueAt(productsTable.getSelectedRow(), 0));
	}

	public int searchByNameInTable(int id) {
		for (int i = 0; i < productsTable.getRowCount(); i++) {
			if (((int) productsTable.getValueAt(i, 0)) == id) {
				return i;
			}
		}
		return -1;
	}
}