package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.robotz.view.texture.Ground;
import com.robotz.view.texture.Obstacle;
import com.robotz.view.texture.Robot;

public class AnimationJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextArea productionOutput;
	private JPanel mainPanel;
	private String productionIncremental = "";
	
	private ArrayList<ArrayList<JPanel>> tiles = new ArrayList<ArrayList<JPanel>>();
	
	private int mapWidth;
	private int mapHeight;

	public AnimationJFrame() {
		
		setTitle("Animation");
		setVisible(false);
		setBackground(Color.WHITE);
		setSize(800, 800);
		setPreferredSize(new Dimension(800, 800));
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		initComponent();
		pack();
		
	}
	
	private void initComponent() {
		
		setLayout(new BorderLayout());
		
		productionOutput = new JTextArea();
		
		productionOutput.setRows(6);
		
		productionOutput.setEditable(false);
		
		add(new JScrollPane(productionOutput), BorderLayout.SOUTH);
		
		mainPanel = new JPanel();
		
		JScrollPane mainScrollPane = new JScrollPane(mainPanel);
		
		mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		add(mainScrollPane, BorderLayout.CENTER);
		
	}
	
	public void initAnimation(int mapWidth, int mapHeight) {
		
		this.mapWidth = mapWidth;
		
		this.mapHeight = mapHeight;
		
		mainPanel.setLayout(new GridBagLayout());
		
		for (int i=0; i<mapHeight; i++) {
			
			ArrayList<JPanel> row = new ArrayList<JPanel>();
			
			for (int k=0; k<mapWidth; k++) {
				
				row.add(new Ground());
				
			}
			
			tiles.add(row);
			
		}
		
		reRenderGraphics();
				
	}
	
	private void reRenderGraphics() {
		
		mainPanel.removeAll();
		
		GridBagConstraints c = new GridBagConstraints();
		
		for (int i=0; i<mapHeight; i++) {
			
			for (int k=0; k<mapWidth; k++) {
				
				c.gridx = i;
				c.gridy = k;
				
				mainPanel.add(tiles.get(i).get(k), c);
				
			}
			
		}
		
		mainPanel.revalidate();
				
	}
	
	public void clear() {
		
		productionOutput.setText("");
		productionIncremental = "";
		tiles.clear();
		
	}
	
	public void addDescription(String productionRule) {
		
		productionIncremental += productionRule + "\n";
		
		productionOutput.setText(productionIncremental);
		
	}
	
	public void addObstacle(int xPosition, int yPosition) {
		
		tiles.get(xPosition - 1).set(yPosition - 1, new Obstacle(xPosition, yPosition));
		
		reRenderGraphics();
		
	}
	
	public void addRobot(String robotName, int xPosition, int yPosition) {
		
		//initial robot heading is north
		tiles.get(xPosition - 1).set(yPosition - 1, new Robot(robotName, xPosition, yPosition));
		
		reRenderGraphics();
		
	}

}
