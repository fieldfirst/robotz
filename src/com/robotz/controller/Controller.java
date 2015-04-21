package com.robotz.controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.robotz.model.EditorModel;
import com.robotz.view.AnimationPanel;
import com.robotz.view.EditorJFrame;
import com.robotz.view.ErrorDialog;

public class Controller {
	
	protected static EditorJFrame frmMain;
	protected EditorModel editorModel;
	protected static ErrorDialog errorDialog;
	protected static AnimationPanel animationPanel;
	
	public Controller() {
		initAction();
		assignMenuAction();
		assignToolBarAction();
	}
	
	protected void initAction() {
		frmMain = new EditorJFrame();
		errorDialog = ErrorDialog.getInstance();
		animationPanel = frmMain.getAnimationPanel();
		
		frmMain.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentMoved(ComponentEvent arg0) {
				errorDialog.setLocationRelativeTo(frmMain);
			}
		});
		
		new FileController();
		new CommandController();
		new ViewController();
		new HelpController();
		new AddController();
		new EditController();
	}
	
	protected void assignMenuAction() {

	}
	
	protected void assignToolBarAction() {

	}
}