package com.robotz.view;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class AnimationPanel extends JPanel {

	private static final long serialVersionUID = -9223354302216216810L;
	
	private final int WIDTH = 60;
	private final int HEIGHT = 60;
	
	private Texture[][] texture;
	
	public AnimationPanel() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
	}
	
	public void createTextures() {
		// Lazy textures initialization
		if (texture == null) {
			texture = new Texture[HEIGHT][WIDTH];
			for (int i=0; i<HEIGHT; i++) {
				for (int j=0; j<WIDTH; j++) {
					texture[i][j] = new Texture();
					add(texture[i][j]);
				}
			}
		}
	}

}
