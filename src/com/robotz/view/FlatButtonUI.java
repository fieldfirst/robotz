package com.robotz.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;

public class FlatButtonUI extends BasicButtonUI{
	
	private Color pressedColor;
	private Color normalColor;

	public FlatButtonUI(Color pressed, Color normal) {
		super();
		this.pressedColor = pressed;
		this.normalColor = normal;
	}

	@Override
	public void paint(Graphics arg0, JComponent arg1) {
		Graphics2D g2D = (Graphics2D) arg0;
		g2D.setColor(normalColor);
		g2D.drawRect(0, arg1.getY(), arg1.getWidth(), arg1.getHeight());
		g2D.fillRect(0, arg1.getY(), arg1.getWidth(), arg1.getHeight());
	}

	@Override
	protected void paintButtonPressed(Graphics arg0, AbstractButton arg1) {
		Graphics2D g2D = (Graphics2D) arg0;
		g2D.setColor(pressedColor);
		g2D.drawRect(0, arg1.getY(), arg1.getWidth(), arg1.getHeight());
		g2D.fillRect(0, arg1.getY(), arg1.getWidth(), arg1.getHeight());
	}

}
