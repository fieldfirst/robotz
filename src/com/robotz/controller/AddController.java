package com.robotz.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import com.robotz.view.dialog.AddAddDialog;
import com.robotz.view.dialog.AddDialog;
import com.robotz.view.dialog.AddDoDialog;
import com.robotz.view.dialog.AddMoveDialog;
import com.robotz.view.dialog.AddObstacleDialog;
import com.robotz.view.dialog.AddRobotDialog;
import com.robotz.view.dialog.AddVariableDialog;

public class AddController extends Controller {
	
	private AddRobotAction addRobotAction;
	private AddObstacleAction addObstacleAction;
	private AddAddAction addAddAction;
	private AddMoveAction addMoveAction;
	private AddVariableAction addVariableAction;
	private AddDoAction addDoAction;
	
	private AddDialog dialog;
	
	protected void initAction() {
		addRobotAction = new AddRobotAction();
		addObstacleAction = new AddObstacleAction();
		addAddAction = new AddAddAction();
		addMoveAction = new AddMoveAction();
		addVariableAction = new AddVariableAction();
		addDoAction = new AddDoAction();
	}
	
	protected void assignToolBarAction() {
		frmMain.setToolBarRobot(addRobotAction);
		frmMain.setToolBarObstacle(addObstacleAction);
		frmMain.setToolBarAdd(addAddAction);
		frmMain.setToolBarMove(addMoveAction);
		frmMain.setToolBarVariable(addVariableAction);
		frmMain.setToolBarDo(addDoAction);
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
			dialog = new AddRobotDialog(frmMain);
			if (dialog.showDialog()) {
				frmMain.setTabIndex(0);
				frmMain.setInsertText("robot " + dialog.getResult()[0] + " " + dialog.getResult()[1] + " " + dialog.getResult()[2] + " ;");
			}
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
			dialog = new AddObstacleDialog(frmMain);
			if (dialog.showDialog()) {
				frmMain.setTabIndex(0);
				frmMain.setInsertText("obstacle " + dialog.getResult()[0] + " " + dialog.getResult()[1] + " ;");
			}
		}
		
	}
	
	private class AddAddAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private AddAddAction(){
			super("Add", new ImageIcon(frmMain.getClass().getResource("resources/add.png")));
			putValue(SHORT_DESCRIPTION, "Add a new add command");
			//putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Force switch to the Editor tab then append this string at the current cursor position
			dialog = new AddAddDialog(frmMain);
			if (dialog.showDialog()) {
				frmMain.setTabIndex(0);
				frmMain.setInsertText("add " + dialog.getResult()[0] + " to " + dialog.getResult()[1] + " ;");
			}
		}
		
	}
	
	private class AddMoveAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private AddMoveAction(){
			super("Move", new ImageIcon(frmMain.getClass().getResource("resources/move.png")));
			putValue(SHORT_DESCRIPTION, "Add a new move command");
			//putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Force switch to the Editor tab then append this string at the current cursor position
			dialog = new AddMoveDialog(frmMain);
			if (dialog.showDialog()) {
				frmMain.setTabIndex(0);
				frmMain.setInsertText("move " + dialog.getResult()[0] + " " + dialog.getResult()[1] + " " + dialog.getResult()[2] + " ;");
			}
		}
		
	}
	
	private class AddVariableAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private AddVariableAction(){
			super("Variable", new ImageIcon(frmMain.getClass().getResource("resources/variable.png")));
			putValue(SHORT_DESCRIPTION, "Add a new variable");
			//putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Force switch to the Editor tab then append this string at the current cursor position
			dialog = new AddVariableDialog(frmMain);
			if (dialog.showDialog()) {
				frmMain.setTabIndex(0);
				frmMain.setInsertText(dialog.getResult()[0] + " = " + dialog.getResult()[1] + " ;");
			}
		}
		
	}
	
	private class AddDoAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private AddDoAction(){
			super("Do", new ImageIcon(frmMain.getClass().getResource("resources/do.png")));
			putValue(SHORT_DESCRIPTION, "Add a new do command");
			//putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Force switch to the Editor tab then append this string at the current cursor position
			dialog = new AddDoDialog(frmMain);
			if (dialog.showDialog()) {
				frmMain.setTabIndex(0);
				frmMain.setInsertText("do\n\nuntil " + dialog.getResult()[0] + " > " + dialog.getResult()[1] + " ;");
			}
		}
		
	}

}
