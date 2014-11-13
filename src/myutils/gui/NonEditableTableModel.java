package myutils.gui;

import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {

	public NonEditableTableModel(int i, int j) {
		super(i, j);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		// TODO Auto-generated method stub
		// return super.isCellEditable(row, column);
		return false;
	}
}
