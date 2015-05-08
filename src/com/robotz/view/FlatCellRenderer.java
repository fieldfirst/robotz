package com.robotz.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class FlatCellRenderer extends DefaultTableCellRenderer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Color even = new Color(178, 223, 219);
	private final Color odd = new Color(224, 242, 241);
	private final Color focused = new Color(77, 182, 172);
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (row % 2 == 0) {
			c.setBackground(even);
		}
		else
		{
			c.setBackground(odd);
		}
		if (isSelected) {
			c.setBackground(focused);
		}
		return c;
	}

}
