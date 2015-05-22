package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class UserManualJFrame extends JDialog {
	

	private static final long serialVersionUID = 1L;
	

	public UserManualJFrame() {
		
		setLayout(new BorderLayout());
		
		add(new panelBackground(), BorderLayout.CENTER);
		
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton cancelButton = new JButton("ok");
		
		bottomPanel.add(cancelButton);
		
		add(bottomPanel, BorderLayout.SOUTH);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				dispose();
				
			}
			
		});
		
		setVisible(true);
		
		pack();
	
	}
	
	private class panelBackground extends JPanel {
		
		private static final long serialVersionUID = 1L;
		
		private Image background;
		
		public panelBackground() {
			
			ImageIcon imageIcon = new ImageIcon(getClass().getResource("resources/user_manual.png"));
			
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
		
	}

}
