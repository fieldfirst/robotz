package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

public class AnimationJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextArea productionOutput;
	private JPanel mainPanel;
	private JScrollPane mainScrollPane;

	private ArrayList<ArrayList<JPanel>> tiles = new ArrayList<ArrayList<JPanel>>();

	private HashMap<String, Robot> robotMap = new HashMap<String, Robot>();

	private int mapWidth;
	private int mapHeight;

	private JSlider speedSlider;

	private ErrorDialog errorDialog;
	private EditorJFrame frmMain;

	private UpdateViewPortThread updateViewPort;

	public AnimationJFrame(ErrorDialog errorDialog, EditorJFrame frmMain) {

		setTitle("Animation");
		setVisible(false);
		setBackground(Color.WHITE);
		setSize(800, 800);
		setPreferredSize(new Dimension(800, 800));
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		initComponent();
		pack();

		this.errorDialog = errorDialog;
		this.frmMain = frmMain;

		updateViewPort = new UpdateViewPortThread(this);

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

		mainScrollPane = new JScrollPane(mainPanel);

		mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		mainScrollPane.setViewportView(mainPanel);

		add(mainScrollPane, BorderLayout.CENTER);

		speedSlider = new JSlider(700, 1500, 1000);

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

		for (int i=0; i<mapWidth; i++) {

			ArrayList<JPanel> row = new ArrayList<JPanel>();

			for (int k=0; k<mapHeight; k++) {

				row.add(new Ground());

			}

			tiles.add(row);

		}

		reRenderGraphics();

	}

	private void reRenderGraphics() {

		mainPanel.removeAll();

		GridBagConstraints c = new GridBagConstraints();

		for (int i=0; i<mapWidth; i++) {

			for (int k=0; k<mapHeight; k++) {

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
		robotMap.clear();

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

		if (xPosition <= getMaximumX() && yPosition <= getMaximumY()) {

			tiles.get(xPosition).set(yPosition, new Obstacle(xPosition, yPosition));

		}
		else {

			showObstacleOutOfBoundError(xPosition, yPosition);

		}

		reRenderGraphics();

	}

	public void addRobot(String robotName, int xPosition, int yPosition) {

		if (checkDuplicateRobot(robotName)) {

			if (xPosition <= getMaximumX() && yPosition <= getMaximumY()) {

				//initial robot heading is north
				tiles.get(xPosition).set(yPosition, new Robot(robotName, xPosition, yPosition, this));

				//add the robot to the robotMap HashMap for later call
				robotMap.put(robotName, (Robot) tiles.get(xPosition).get(yPosition));

			}
			else {

				showRobotOutOfBoundError(robotName, xPosition, yPosition);

			}

		}

		reRenderGraphics();

	}

	public void moveRobot(String robotName, String direction, int distance, int threadSleepTime) throws InterruptedException {

		// Get the robot
		final Robot robot = resolveRobot(robotName);

		// Set a new heading
		robot.setDirection(direction);

		int walked = 0;

		while (walked < distance) {

			// focusing the moved robot
			updateViewPort.setRobot(robot);
			SwingUtilities.invokeLater(updateViewPort);

			robot.move(tiles);

			walked++;

			reRenderGraphics();
			
			// Attempt to fix the glitches
			mainScrollPane.repaint();
			
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

	private Robot resolveRobot(String robotName) {

		if (robotMap.containsKey(robotName))

			return robotMap.get(robotName);

		else {

			errorDialog.appendError("Unknow robot : " + robotName);

			errorDialog.setVisible(true);

			errorDialog.setLocationRelativeTo(frmMain);

			Thread.currentThread().interrupt();

			return null;

		}

	}

	private boolean checkDuplicateRobot(String robotName) {

		if (! robotMap.containsKey(robotName))

			return true;

		else {

			errorDialog.appendError("Duplicate robot : " + robotName);

			errorDialog.setVisible(true);

			errorDialog.setLocationRelativeTo(frmMain);

			Thread.currentThread().interrupt();

			return false;

		}

	}

	private void showObstacleOutOfBoundError(int x, int y) {

		errorDialog.appendError("Attempt to create an obstacle out of map boundary at : " + x + " , " + y);

		errorDialog.setVisible(true);

		errorDialog.setLocationRelativeTo(frmMain);

		Thread.currentThread().interrupt();

	}

	private void showRobotOutOfBoundError(String robotName, int x, int y) {

		errorDialog.appendError("Attempt to create a robot out of map boundary : " + robotName + " at " + x + " , " + y);

		errorDialog.setVisible(true);

		errorDialog.setLocationRelativeTo(frmMain);

		Thread.currentThread().interrupt();

	}
	
	private class UpdateViewPortThread implements Runnable {
		
		private Robot robot;
		private AnimationJFrame animationWindow;
		
		public UpdateViewPortThread(AnimationJFrame a) {
			
			animationWindow = a;
			
		}
		
		public void setRobot(Robot r) {
			
			robot = r;
			
		}

		@Override
		public void run() {
			ReentrantLock locker = new ReentrantLock();
			locker.lock();
			Rectangle bound = robot.getBounds();
			bound.setSize(animationWindow.getWidth() / 2, animationWindow.getHeight() / 2);
			bound.setLocation(bound.x - animationWindow.getWidth() / 4, bound.y - animationWindow.getHeight() / 4);
			mainPanel.scrollRectToVisible(bound);
			locker.unlock();
		}
		
	}

}
