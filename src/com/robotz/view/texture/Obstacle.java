package com.robotz.view.texture;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Obstacle extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image obstacle;
	
	private int xPosition;
	
	private int yPosition;
	
	public Obstacle(int xPosition, int yPosition) {
		
		ImageIcon obstacleTexture = new ImageIcon(getClass().getResource("../resources/obstacle_texture.png"));
				
		Dimension size = new Dimension(obstacleTexture.getIconWidth(), obstacleTexture.getIconHeight());
		
		obstacle = obstacleTexture.getImage();
		
		setPreferredSize(size);
		
		setMinimumSize(size);
		
		setLayout(null);
		
		this.xPosition = xPosition + 1;
		
		this.yPosition = yPosition + 1;
		
	}

	@Override
	protected void paintComponent(Graphics g) {
				
		g.drawImage(obstacle, 0, 0, null);
		
		g.drawString(xPosition + "," + yPosition, 20, 60);
		
	}

}
