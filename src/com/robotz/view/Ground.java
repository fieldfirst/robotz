package com.robotz.view;

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

		background = imageIcon.getImage();

		setPreferredSize(AnimationJFrame.textureSize);

		setMinimumSize(AnimationJFrame.textureSize);

		setLayout(null);

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

		g.drawImage(background, 0, 0, width.intValue(), height.intValue(), null);

	}

	@Override
	public String getTextureName() {

		return this.textureName;

	}

}
