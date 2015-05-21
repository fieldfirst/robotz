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
	
	private final Color updated = new Color(237, 108, 31);
	
	private int updatedRow = -1;
	
	private int counter = 0;
	
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
		
		if (row == updatedRow) {
			
			if (counter <= 1) {
			
				c.setBackground(updated);
			
				counter++;
			
			}
		
			else if (counter == 2) {
			
				c.setBackground(updated);
			
				updatedRow = -1;
			
				counter = 0;
			
			}
		}
		
		return c;
	}

	public void setUpdatedRow(int updatedRow) {
		
		this.updatedRow = updatedRow;
		
	}
	
}
