package com.robotz.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.AbstractButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class FlatToolBarButtonUI extends BasicButtonUI{

	public FlatToolBarButtonUI() {
		super();
	}

	@Override
	protected void paintButtonPressed(Graphics arg0, AbstractButton arg1) {
		Graphics2D g2D = (Graphics2D) arg0;
		g2D.setColor(new Color(178, 223, 219));
		g2D.drawRect(0, arg1.getY(), arg1.getWidth(), arg1.getHeight());
		g2D.fillRect(0, arg1.getY(), arg1.getWidth(), arg1.getHeight());
	}

}
