package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.robotz.model.EditorModel;
import com.robotz.view.EditorJFrame;

public class EditorController {
	
	private EditorJFrame frmMain;
	private EditorModel editorModel;
	private FileManager fileManager;
	
	private FileOpenAction fileOpenAction;
	private FileNewAction fileNewAction;
	private FileSaveAction fileSaveAction;
	private FileSaveAsAction fileSaveAsAction;
	private FileExitAction fileExitAction;
	private CommandTokenizeAction commandTokenizeAction;
	private CommandCompileAction commandCompileAction;
	private CommandExecuteAction commandExecuteAction;
	private ViewEditorAction viewEditorAction;
	private ViewSymbolTableAction viewSymbolTableAction;
	private ViewAnimationAction viewAnimationAction;
	private ViewConfigurationAction viewConfigurationAction;
	private ViewErrorAction viewErrorAction;
	private HelpReleaseNoteAction helpReleaseNoteAction;
	private HelpShowAnIntroductionDialog helpShowAnIntroductionDialogAction;
	private HelpAboutMeAction helpAboutMeAction;
	
	public EditorController() {
		frmMain = new EditorJFrame();
		initAction();
		assignMenuAction();
		assignToolBarAction();
		frmMain.setTabChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (frmMain.getTabIndex() == 0) {
					viewEditorAction.setEnabled(false);
					viewSymbolTableAction.setEnabled(true);
					viewAnimationAction.setEnabled(true);
				}
				else if (frmMain.getTabIndex() == 1) {
					viewEditorAction.setEnabled(true);
					viewSymbolTableAction.setEnabled(false);
					viewAnimationAction.setEnabled(true);
				}
				else
				{
					viewEditorAction.setEnabled(true);
					viewSymbolTableAction.setEnabled(true);
					viewAnimationAction.setEnabled(false);
				}
			}
			
		});
	}
	
	private void initAction(){
		fileOpenAction = new FileOpenAction();
		fileNewAction = new FileNewAction();
		fileSaveAction = new FileSaveAction();
		fileSaveAsAction = new FileSaveAsAction();
		fileExitAction = new FileExitAction();
		commandTokenizeAction = new CommandTokenizeAction();
		commandCompileAction = new CommandCompileAction();
		commandExecuteAction = new CommandExecuteAction();
		viewEditorAction = new ViewEditorAction();
		viewSymbolTableAction = new ViewSymbolTableAction();
		viewAnimationAction = new ViewAnimationAction();
		viewConfigurationAction = new ViewConfigurationAction();
		viewErrorAction = new ViewErrorAction();
		helpReleaseNoteAction = new HelpReleaseNoteAction();
		helpShowAnIntroductionDialogAction = new HelpShowAnIntroductionDialog();
		helpAboutMeAction = new HelpAboutMeAction();
	}
	
	private void assignMenuAction(){
		frmMain.setMnuOpenAction(fileOpenAction);
		frmMain.setMnuNewAction(fileNewAction);
		frmMain.setMnuSaveAction(fileSaveAction);
		frmMain.setMnuSaveAsAction(fileSaveAsAction);
		frmMain.setMnuExitAction(fileExitAction);
		frmMain.setMnuTokenizeAction(commandTokenizeAction);
		frmMain.setMnuCompileAction(commandCompileAction);
		frmMain.setMnuExecuteAction(commandExecuteAction);
		frmMain.setMnuEditorAction(viewEditorAction);
		frmMain.setMnuSymbolTableAction(viewSymbolTableAction);
		frmMain.setMnuAnimationAction(viewAnimationAction);
		frmMain.setMnuErrorAction(viewErrorAction);
		frmMain.setMnuConfigAction(viewConfigurationAction);
		frmMain.setMnuReleaseNoteAction(helpReleaseNoteAction);
		frmMain.setMnuShowIntroductionDialogAction(helpShowAnIntroductionDialogAction);
		frmMain.setMnuAboutAction(helpAboutMeAction);
	}
	
	private void assignToolBarAction() {
		frmMain.setToolBarNewAction(fileNewAction);
		frmMain.setToolBarOpenAction(fileOpenAction);
		frmMain.setToolBarSaveAction(fileSaveAction);
		frmMain.setToolBarTokenizeAction(commandTokenizeAction);
		frmMain.setToolBarCompileAction(commandCompileAction);
		frmMain.setToolBarExecute(commandExecuteAction);
	}
	
	private class FileNewAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private FileNewAction(){
			super("New", new ImageIcon(frmMain.getClass().getResource("resources/new.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Create a new file");
			putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class FileOpenAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private FileOpenAction(){
			super("Open", new ImageIcon(frmMain.getClass().getResource("resources/open.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Open an existed file");
			putValue(MNEMONIC_KEY, KeyEvent.VK_O);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class FileSaveAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private FileSaveAction(){
			super("Save", new ImageIcon(frmMain.getClass().getResource("resources/save.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Save a change");
			putValue(MNEMONIC_KEY, KeyEvent.VK_S);
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class FileSaveAsAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private FileSaveAsAction(){
			super("Save As", new ImageIcon(frmMain.getClass().getResource("resources/save.png")));
			putValue(SHORT_DESCRIPTION, "Save a new file");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class FileExitAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private FileExitAction(){
			super("Exit", new ImageIcon(frmMain.getClass().getResource("resources/exit.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
			putValue(SHORT_DESCRIPTION, "Exit the program");
			putValue(MNEMONIC_KEY, KeyEvent.VK_X);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
		
	}
	
	private class CommandTokenizeAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private CommandTokenizeAction(){
			super("Tokenize", new ImageIcon(frmMain.getClass().getResource("resources/tokenize.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Invoke a tokenizer");
			putValue(MNEMONIC_KEY, KeyEvent.VK_T);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class CommandCompileAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private CommandCompileAction(){
			super("Compile", new ImageIcon(frmMain.getClass().getResource("resources/compile.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt C"));
			putValue(SHORT_DESCRIPTION, "Invoke a compiler");
			putValue(MNEMONIC_KEY, KeyEvent.VK_P);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class CommandExecuteAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private CommandExecuteAction(){
			super("Execute", new ImageIcon(frmMain.getClass().getResource("resources/execute.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Execute the command(s)");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ViewEditorAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewEditorAction(){
			super("Editor", new ImageIcon(frmMain.getClass().getResource("resources/editor.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt E"));
			putValue(SHORT_DESCRIPTION, "Switch to editor");
			putValue(MNEMONIC_KEY, KeyEvent.VK_D);
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmMain.setTabIndex(0);
			setEnabled(false);
			viewSymbolTableAction.setEnabled(true);
			viewAnimationAction.setEnabled(true);
		}
		
	}
	
	private class ViewSymbolTableAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewSymbolTableAction(){
			super("Symbol Table", new ImageIcon(frmMain.getClass().getResource("resources/symbol_table.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt S"));
			putValue(SHORT_DESCRIPTION, "View a symbol table");
			putValue(MNEMONIC_KEY, KeyEvent.VK_Y);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmMain.setTabIndex(1);
			setEnabled(false);
			viewEditorAction.setEnabled(true);
			viewAnimationAction.setEnabled(true);
		}
		
	}
	
	private class ViewAnimationAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewAnimationAction(){
			super("Animation", new ImageIcon(frmMain.getClass().getResource("resources/animation.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt A"));
			putValue(SHORT_DESCRIPTION, "View an animation");
			putValue(MNEMONIC_KEY, KeyEvent.VK_M);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmMain.setTabIndex(2);
			setEnabled(false);
			viewEditorAction.setEnabled(true);
			viewSymbolTableAction.setEnabled(true);
		}
		
	}
	
	private class ViewErrorAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewErrorAction(){
			super("Error", new ImageIcon(frmMain.getClass().getResource("resources/error_toolbar.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Show an error");
			putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ViewConfigurationAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewConfigurationAction(){
			super("Configuration", new ImageIcon(frmMain.getClass().getResource("resources/configuration.png")));
			putValue(SHORT_DESCRIPTION, "View the configuation panel");
			putValue(MNEMONIC_KEY, KeyEvent.VK_G);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class HelpReleaseNoteAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private HelpReleaseNoteAction(){
			super("Release Note", new ImageIcon(frmMain.getClass().getResource("resources/release_note.png")));
			putValue(SHORT_DESCRIPTION, "View the release note");
			putValue(MNEMONIC_KEY, KeyEvent.VK_L);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class HelpShowAnIntroductionDialog extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private HelpShowAnIntroductionDialog(){
			super("Show the introduction dialog", new ImageIcon(frmMain.getClass().getResource("resources/introduction.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F1"));
			putValue(SHORT_DESCRIPTION, "View the introduction dialog");
			putValue(MNEMONIC_KEY, KeyEvent.VK_I);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class HelpAboutMeAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private HelpAboutMeAction(){
			super("About Me", new ImageIcon(frmMain.getClass().getResource("resources/about_me.png")));
			putValue(SHORT_DESCRIPTION, "View the developer's info");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}