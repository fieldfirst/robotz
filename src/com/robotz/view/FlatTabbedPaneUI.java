package com.robotz.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.plaf.basic.BasicTabbedPaneUI;

import Main.RobotMain;

public class FlatTabbedPaneUI extends BasicTabbedPaneUI {
	
	public FlatTabbedPaneUI() {
		super();
	}

	@Override
	protected void paintTabBackground(Graphics g, int tabPlacement,
			int tabIndex, int x, int y, int w, int h, boolean isSelected) {
		Graphics2D g2 = (Graphics2D) g;
		if (isSelected)
			g2.setColor(new Color(0, 121, 107));
		else
			g2.setColor(new Color(77, 182, 172));
		g2.drawRect(x, y, w, h);
		g2.fillRect(x, y, w, h);
	}

	@Override
	protected Insets getContentBorderInsets(int tabPlacement) {
		return new Insets(7, 0, 0, 0);
	}

	@Override
	protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex,
			int x, int y, int w, int h, boolean isSelected) {
		// DO NOTHING
	}

	@Override
	protected void paintText(Graphics g, int tabPlacement, Font font,
			FontMetrics metrics, int tabIndex, String title,
			Rectangle textRect, boolean isSelected) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		g2.drawString(title, textRect.x , textRect.y + metrics.getAscent());
	}
	
	@Override
    protected LayoutManager createLayoutManager() {
		if (RobotMain.getOSName().contains("win"))
			return new MyTabbedPaneLayout();
		else
			return super.createLayoutManager();
    }

    protected class MyTabbedPaneLayout extends TabbedPaneLayout {
        @Override
        protected void padSelectedTab(int tabPlacement, int selectedIndex) {
            //do nothing!
            //super.padSelectedTab(tabPlacement, selectedIndex);
        }
    }

}
