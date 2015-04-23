package com.robotz.view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DerivationTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 256148375662108254L;
	private ArrayList<Object[]> data = new ArrayList<Object[]>();
	private String[] columnNames = {"Production", "Derivation"};
	
	public void addItem(String step, String derivation) {
		data.add(new Object[]{step, derivation});
	}
	
	public void clearTable() {
		data.clear();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data.get(row)[col];
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

}
