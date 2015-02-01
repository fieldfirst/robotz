package com.robotz.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class TabUI extends BasicTabbedPaneUI{
	
	private Color themeColor;
	private Color backColor;
	
	private int tabNum;
	
	public TabUI(Color colorScheme) {
		super();
		themeColor = colorScheme;
		backColor = new Color(40, 44, 49);
	}
	
	protected void paintTabBackground(Graphics g,
            int tabPlacement,
            int tabIndex,
            int x,
            int y,
            int w,
            int h,
            boolean isSelected) {
		if (isSelected) {
			g.setColor(themeColor);
			g.drawRect(x, y, 60, h);
			g.fillRect(x, y, 60, h);
		}
		else
		{
			g.setColor(backColor);
			g.drawRect(x, y, w, h);
			g.fillRect(x, y, w, h);
		}
	}

}
