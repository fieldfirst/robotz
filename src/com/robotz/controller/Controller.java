package com.robotz.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;

import com.robotz.view.FrmMain;

public class Controller {
	
	private FrmMain frmMain;
	private boolean closePromptFlag;
	
	private ActionMap mainActionMap;
	private FileMenuAction fileMenuAction;
	
	public Controller() {
		frmMain = new FrmMain();
		closePromptFlag = false;
		assignMenuActionMap();
	}
	
	private void assignMenuActionMap() {
		fileMenuAction = new FileMenuAction("File");

	}
	
	private class FileMenuAction extends AbstractAction {
		
		public FileMenuAction(String name) {
			super(name);
		}

		private static final long serialVersionUID = 6541596230113846836L;

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(arg0.getActionCommand());
			System.out.println("testZZ");
		}
		
	}
}