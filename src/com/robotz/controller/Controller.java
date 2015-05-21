package com.robotz.controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.robotz.model.EditorModel;
import com.robotz.view.AnimationJFrame;
import com.robotz.view.EditorJFrame;
import com.robotz.view.ErrorDialog;

public class Controller {
	
	protected static EditorJFrame frmMain;
	protected EditorModel editorModel;
	protected static ErrorDialog errorDialog;
	protected static AnimationJFrame animationDialog;
	
	protected static FileController fileController;
	protected static CommandController commandController;
	protected static ViewController viewController;
	protected static HelpController helpController;
	protected static AddController addController;
	protected static EditController editController;
	
	public Controller() {
		initAction();
		assignMenuAction();
		assignToolBarAction();
	}
	
	protected void initAction() {
		frmMain = new EditorJFrame();
		errorDialog = ErrorDialog.getInstance();
		animationDialog = new AnimationJFrame();
		
		frmMain.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentMoved(ComponentEvent arg0) {
				errorDialog.setLocationRelativeTo(frmMain);
			}
		});
		
		fileController = new FileController();
		commandController = new CommandController();
		viewController = new ViewController();
		helpController = new HelpController();
		addController = new AddController();
		editController = new EditController();
	}
	
	protected void assignMenuAction() {

	}
	
	protected void assignToolBarAction() {

	}
}