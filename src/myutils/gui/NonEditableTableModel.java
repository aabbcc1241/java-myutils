package myutils.gui;

import javax.swing.table.DefaultTableModel;

public class NonEditableTableModel extends DefaultTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NonEditableTableModel(int i, int j) {
        super(i, j);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
