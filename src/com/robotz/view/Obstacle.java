package com.robotz.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Obstacle extends JPanel implements Texture {

	private static final long serialVersionUID = 1L;
	
	private Image obstacle;
	
	private int xPosition;
	
	private int yPosition;
	
	private final String textureName = "Obstacle";
	
	public Obstacle(int xPosition, int yPosition) {
		
		ImageIcon obstacleTexture = new ImageIcon(getClass().getResource("resources/obstacle_texture.png"));
				
		Dimension size = new Dimension(obstacleTexture.getIconWidth(), obstacleTexture.getIconHeight());
		
		obstacle = obstacleTexture.getImage();
		
		setPreferredSize(size);
		
		setMinimumSize(size);
		
		setLayout(null);
		
		this.xPosition = xPosition;
		
		this.yPosition = yPosition;
		
	}

	@Override
	protected void paintComponent(Graphics g) {
				
		g.drawImage(obstacle, 0, 0, null);
		
		g.drawString(xPosition + "," + yPosition, 20, 60);
		
	}
	
	public String getTextureName() {
		
		return this.textureName;
		
	}

}
