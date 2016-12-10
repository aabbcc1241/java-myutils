package myutils.gui;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("UnusedDeclaration")
public class NonEditableTableModel extends DefaultTableModel {
  public NonEditableTableModel(int i, int j) {
    super(i, j);
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
