package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

public class AnimationJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextArea productionOutput;
	private JPanel mainPanel;
	
	private ArrayList<ArrayList<JPanel>> tiles = new ArrayList<ArrayList<JPanel>>();
	
	private HashMap<String, Robot> robotMap = new HashMap<String, Robot>();
	
	private int mapWidth;
	private int mapHeight;
	
	JSlider speedSlider;

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
		
		DefaultCaret caret = (DefaultCaret) productionOutput.getCaret();
		
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		add(new JScrollPane(productionOutput), BorderLayout.SOUTH);
		
		mainPanel = new JPanel();
		
		JScrollPane mainScrollPane = new JScrollPane(mainPanel);
		
		mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		add(mainScrollPane, BorderLayout.CENTER);
		
		speedSlider = new JSlider(500, 1500, 1000);
		
		speedSlider.setMinorTickSpacing(2);
		
		speedSlider.setMajorTickSpacing(10);
		
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		topPanel.add(new JLabel("Speed : "));
		
		topPanel.add(new JLabel("fast"));
		
		topPanel.add(speedSlider);
		
		topPanel.add(new JLabel("slow"));
		
		add(topPanel, BorderLayout.NORTH);
		
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
		tiles.clear();
		
	}
	
	public void addDescription(String productionRule) {
		
		productionOutput.append(productionRule + "\n");
		
	}
	
	public void setSpeedSliderListener(ChangeListener listener) {
		
		speedSlider.addChangeListener(listener);
		
	}
	
	public int getSpeedSliderValue() {
		
		return speedSlider.getValue();
		
	}
	
	public void addObstacle(int xPosition, int yPosition) {
		
		tiles.get(xPosition).set(yPosition, new Obstacle(xPosition, yPosition));
		
		reRenderGraphics();
		
	}
	
	public void addRobot(String robotName, int xPosition, int yPosition) {
		
		//initial robot heading is north
		tiles.get(xPosition).set(yPosition, new Robot(robotName, xPosition, yPosition, this));
		
		//add the robot to the robotMap HashMap for later call
		robotMap.put(robotName, (Robot) tiles.get(xPosition).get(yPosition));
		
		reRenderGraphics();
		
	}
	
	public void moveRobot(String robotName, String direction, int distance, int threadSleepTime) throws InterruptedException {
		
		// Get the robot
		Robot robot = robotMap.get(robotName);
		
		// Set a new heading
		robot.setDirection(direction);
		
		int walked = 0;
		
		while (walked < distance) {
			
			robot.move(tiles);
			
			walked++;
			
			reRenderGraphics();
			
			addDescription("move " + robot.getRobotName() + " from (" + robot.getOldXPosition() + "," + robot.getOldYPosition() + ") to (" + robot.getXPosition() + "," + robot.getYPosition() + ")");
			
			Thread.sleep(threadSleepTime);
			
		}
		
	}
	
	public int getMaximumY() {
				
		return tiles.get(0).size() - 1;
		
	}
	
	public int getMaximumX() {
				
		return tiles.size() - 1;
		
	}
}
