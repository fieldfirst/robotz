package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import com.robotz.model.EditorModel;
import com.robotz.view.EditorJFrame;

public class EditorController {
	
	private EditorJFrame frmMain;
	private EditorModel editorModel;
	
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
		frmMain.getMainToolBar().add(fileNewAction);
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
		frmMain.getMnuOpen().setAction(fileOpenAction);
		frmMain.getMnuNew().setAction(fileNewAction);
		frmMain.getMnuSave().setAction(fileSaveAction);
		frmMain.getMnuSaveAs().setAction(fileSaveAsAction);
		frmMain.getMnuExit().setAction(fileExitAction);
		frmMain.getMnuTokenize().setAction(commandTokenizeAction);
		frmMain.getMnuCompile().setAction(commandCompileAction);
		frmMain.getMnuExecute().setAction(commandExecuteAction);
		frmMain.getMnuEditor().setAction(viewEditorAction);
		frmMain.getMnuSymbolTable().setAction(viewSymbolTableAction);
		frmMain.getMnuAnimation().setAction(viewAnimationAction);
		frmMain.getMnuError().setAction(viewErrorAction);
		frmMain.getMnuConfig().setAction(viewConfigurationAction);
		frmMain.getMnuReleaseNote().setAction(helpReleaseNoteAction);
		frmMain.getMnuShowWelcomeDialog().setAction(helpShowAnIntroductionDialogAction);
		frmMain.getMnuAbout().setAction(helpAboutMeAction);
	}
	
	private class FileNewAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private FileNewAction(){
			super("New", new ImageIcon("./resources/new.png"));
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
			super("Open", new ImageIcon("./resources/open.png"));
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
			super("Save", new ImageIcon("./resources/save.png"));
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
			super("Save As", new ImageIcon("./resources/save.png"));
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
			super("Exit", new ImageIcon("./resources/exit.png"));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
			putValue(SHORT_DESCRIPTION, "Exit the program");
			putValue(MNEMONIC_KEY, KeyEvent.VK_X);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class CommandTokenizeAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private CommandTokenizeAction(){
			super("Tokenize", new ImageIcon("./resources/tokenize.png"));
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
			super("Compile", new ImageIcon("./resources/compile.png"));
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
			super("Execute", new ImageIcon("./resources/execute.png"));
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
			super("Editor", new ImageIcon("./resources/editor.png"));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt E"));
			putValue(SHORT_DESCRIPTION, "Switch to editor");
			putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ViewSymbolTableAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewSymbolTableAction(){
			super("Symbol Table", new ImageIcon("./resources/symbol_table.png"));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt S"));
			putValue(SHORT_DESCRIPTION, "View a symbol table");
			putValue(MNEMONIC_KEY, KeyEvent.VK_Y);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ViewAnimationAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewAnimationAction(){
			super("Animation", new ImageIcon("./resources/animation.png"));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt A"));
			putValue(SHORT_DESCRIPTION, "View an animation");
			putValue(MNEMONIC_KEY, KeyEvent.VK_M);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ViewErrorAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewErrorAction(){
			super("Error", new ImageIcon("./resources/error_toolbar.png"));
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
			super("Configuration", new ImageIcon("./resources/configuration.png"));
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
			super("Release Note", new ImageIcon("./resources/release_note.png"));
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
			super("Show the introduction dialog", new ImageIcon("./resources/introduction.png"));
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
			super("About Me", new ImageIcon("./resources/about_me.png"));
			putValue(SHORT_DESCRIPTION, "View the developer's info");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}