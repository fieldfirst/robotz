package com.robotz.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JToggleButton;

public class PopupButton extends JToggleButton {

	private static final long serialVersionUID = 2970376083108855753L;
	
	private Color highlightColor;
	private String imagePath;
	private BufferedImage image;
	
	private static int btnSizeHeight = 34;
	private static int btnSizeWidth = 34;
	
	private static int midWidth;
	private static int midHeight;
	
	public PopupButton(Color highlight, String iconPath) {
		super();
		highlightColor = highlight;
		imagePath = iconPath;
		loadIcon();
		this.setPreferredSize(new Dimension(btnSizeWidth, btnSizeHeight));
	}
	
	private void loadIcon() {
		// Load the image then calculate the drawing coordinate
		try {
			image = ImageIO.read(new File(imagePath));
			midWidth = this.getWidth() / 2 + image.getWidth() / 2;
			midHeight = this.getHeight() / 2 + image.getHeight() / 2;
		} catch (IOException e) {
			System.out.println("Image can't be loaded");
		}
	}
	
	protected void paintComponent(Graphics g) {
		if (this.isSelected()) {
			g.drawOval(0, 0, this.getWidth() - 1, this.getHeight() - 1);
			g.setColor(highlightColor);
			g.fillOval(0, 0, this.getWidth() - 1, this.getHeight() - 1);
			g.drawImage(image, midWidth, midHeight, null);
		}
		else
		{
			g.drawOval(0, 0, this.getWidth() - 1, this.getHeight()-1);
			g.setColor(new Color(35, 36, 37));
			g.fillOval(0, 0, this.getWidth()-1, this.getHeight()-1);
			g.drawImage(image, midWidth, midHeight, null);
		}
	}

}