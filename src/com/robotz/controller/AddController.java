package com.robotz.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

public class AddController extends Controller {
	
	private AddRobotAction addRobotAction;
	private AddObstacleAction addObstacleAction;
	
	protected void initAction() {
		addRobotAction = new AddRobotAction();
		addObstacleAction = new AddObstacleAction();
	}
	
	protected void assignToolBarAction() {
		frmMain.setToolBarRobot(addRobotAction);
		frmMain.setToolBarObstacle(addObstacleAction);
	}
	
	private class AddRobotAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private AddRobotAction(){
			super("Robot", new ImageIcon(frmMain.getClass().getResource("resources/robot.png")));
			putValue(SHORT_DESCRIPTION, "Add a new Robot");
			//putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Force switch to the Editor tab then append this string at the current cursor position
			frmMain.setTabIndex(0);
			frmMain.setInsertText("robot v a b");
		}
		
	}
	
	private class AddObstacleAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private AddObstacleAction(){
			super("Obstacle", new ImageIcon(frmMain.getClass().getResource("resources/obstacle.png")));
			putValue(SHORT_DESCRIPTION, "Add a new Obstacle");
			//putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Force switch to the Editor tab then append this string at the current cursor position
			frmMain.setTabIndex(0);
			frmMain.setInsertText("obstacle a b");
		}
		
	}

}
