package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDialog;

public abstract class FlatDialog extends JDialog{

		private static final long serialVersionUID = -8771397140602186635L;
		protected Color bgColor;
		protected Color foregroundColor;
		
		protected FlatDialog() {
			setPreferredSize(new Dimension(400, 200));
			setMinimumSize(new Dimension(400, 200));
			setTitle("Flat Dialog");
			setVisible(false);
			setAlwaysOnTop(true);
			setUndecorated(true);
			setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
			setLayout(new BorderLayout());
			bgColor = new Color(239, 83, 80);
			foregroundColor = Color.WHITE;
			setBackground(bgColor);
			initComponent();
		}
		
		protected abstract void initComponent();		
}
