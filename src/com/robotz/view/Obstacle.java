package com.robotz.view;

import java.awt.Color;
import java.awt.Font;
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

		obstacle = obstacleTexture.getImage();

		setPreferredSize(AnimationJFrame.textureSize);

		setMinimumSize(AnimationJFrame.textureSize);

		setLayout(null);

		this.xPosition = xPosition;

		this.yPosition = yPosition;

	}

	@Override
	public void updateSize() {

		setPreferredSize(AnimationJFrame.textureSize);

		setMinimumSize(AnimationJFrame.textureSize);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Double width = AnimationJFrame.textureSize.getWidth();

		Double height = AnimationJFrame.textureSize.getHeight();

		g.drawImage(obstacle, 0, 0, width.intValue(), height.intValue(), null);
		
		g.setColor(Color.WHITE);
		
		g.setFont(new Font(g.getFont().getName(), Font.PLAIN, calculateFontSize(width.intValue())));
		
		g.drawString(xPosition + "," + yPosition, width.intValue() - 32, height.intValue() - 10);

	}
	
	private int calculateFontSize(int width) {
		
		if (width <= 64 && width >= 54)
			return LARGE_FONT;
		
		else if (width < 54 && width >= 44)
			return MEDIUM_FONT;
		
		else if (width < 44 && width >= 32)
			return SMALL_FONT;
		
		return 0;
		
	}

	@Override
	public String getTextureName() {

		return this.textureName;

	}

}
