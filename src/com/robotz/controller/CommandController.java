package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class CommandController extends Controller {
	
	private CommandTokenizeAction commandTokenizeAction;
	private CommandCompileAction commandCompileAction;
	private CommandExecuteAction commandExecuteAction;
	
	protected void initAction() {
		commandTokenizeAction = new CommandTokenizeAction();
		commandCompileAction = new CommandCompileAction();
		commandExecuteAction = new CommandExecuteAction();
	}
	
	protected void assignMenuAction() {
		frmMain.setMnuTokenizeAction(commandTokenizeAction);
		frmMain.setMnuCompileAction(commandCompileAction);
		frmMain.setMnuExecuteAction(commandExecuteAction);
	}
	
	protected void assignToolBarAction() {
		frmMain.setToolBarTokenizeAction(commandTokenizeAction);
		frmMain.setToolBarCompileAction(commandCompileAction);
		frmMain.setToolBarExecute(commandExecuteAction);
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

}
