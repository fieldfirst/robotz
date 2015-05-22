package com.robotz.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Ground extends JPanel implements Texture {

	private static final long serialVersionUID = 1L;
	
	private Image background;
	
	private final String textureName = "Ground";
	
	public Ground() {
		
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("resources/ground.png"));
		
		Dimension size = new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight());
		
		background = imageIcon.getImage();
		
		setPreferredSize(size);
		
		setMinimumSize(size);
		
		setLayout(null);
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		
		g.drawImage(background, 0, 0, null);
		
	}
	
	public String getTextureName() {
		
		return this.textureName;
		
	}

}
