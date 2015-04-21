package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import com.robotz.model.ParserContext;
import com.robotz.model.Token;
import com.robotz.model.Tokenizer;

public class CommandController extends Controller {

	private CommandTokenizeAction commandTokenizeAction;
	private CommandCompileAction commandCompileAction;
	private CommandExecuteAction commandExecuteAction;
	
	private ErrorDialogCloseButton errorDialogCloseButton;

	// All tokens are stored here
	private Queue<Token> tokens = new LinkedList<Token>();

	// if there is an error in a previous operation all operation will stop
	private boolean hasError = false;

	protected void initAction() {
		commandTokenizeAction = new CommandTokenizeAction();
		commandCompileAction = new CommandCompileAction();
		commandExecuteAction = new CommandExecuteAction();
		errorDialogCloseButton = new ErrorDialogCloseButton();
	}

	protected void assignMenuAction() {
		frmMain.setMnuTokenizeAction(commandTokenizeAction);
		frmMain.setMnuCompileAction(commandCompileAction);
		frmMain.setMnuExecuteAction(commandExecuteAction);
		
		// special assign the action to the Error dialog's close button
		errorDialog.setbtnCloseAction(errorDialogCloseButton);
	}

	protected void assignToolBarAction() {
		frmMain.setToolBarTokenizeAction(commandTokenizeAction);
		frmMain.setToolBarCompileAction(commandCompileAction);
		frmMain.setToolBarExecute(commandExecuteAction);
	}

	private class CommandTokenizeAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;

		private Tokenizer tokenizer;		
		private final int ERROR = 0;
		private final int VARIABLE_TYPE = 1;
		private final int RESERVED_TYPE = 2;

		private CommandTokenizeAction(){
			super("Tokenize", new ImageIcon(frmMain.getClass().getResource("resources/tokenize.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Invoke a tokenizer");
			putValue(MNEMONIC_KEY, KeyEvent.VK_T);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// Lazy initialization
			if (tokenizer == null) {
				tokenizer = new Tokenizer();
			}

			// Clear the tokens
			tokens.clear();
			frmMain.clearTokenizedItem();
			errorDialog.clearError();

			// Check the tokens line by line
			String[] textPaneText = frmMain.getTextPaneText().split("\n");
			for (int i = 0; i < textPaneText.length; i++) {
				tokenizer.setLine(textPaneText[i], i+1);
				while (tokenizer.hasNextToken()) {
					tokenizer.checkNextToken();
					if (tokenizer.getLastType() == VARIABLE_TYPE) {
						Token tk = tokenizer.getLastToken();
						tokens.add(tk);
						frmMain.addTokenizeItem(tk.getType(), tk.getCharValue(), tk.getIntValue());
					}
					else if (tokenizer.getLastType() == RESERVED_TYPE) {
						tokens.add(tokenizer.getLastToken());
					}
					else if (tokenizer.getLastType() == ERROR) {
						errorDialog.appendError(tokenizer.getLastError());
						hasError = true;
					}
				}
			}
			if (hasError) {
				errorDialog.setVisible(true);
				errorDialog.setLocationRelativeTo(frmMain);
			}
			else {
				errorDialog.appendError("There is no error :)");
			}

			// Switch to the Symbol table tab
			frmMain.setTabIndex(1);
		}

	}

	private class CommandCompileAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;

		private ParserContext parser;

		private CommandCompileAction(){
			super("Compile", new ImageIcon(frmMain.getClass().getResource("resources/compile.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt C"));
			putValue(SHORT_DESCRIPTION, "Invoke a compiler");
			putValue(MNEMONIC_KEY, KeyEvent.VK_P);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			// Before parsing process, we must scan and tokenize first
			commandTokenizeAction.actionPerformed(arg0);

			// If the tokenization process was failed, then don't do anything
			if (! hasError) {
				
				// Lazy initialization
				if (parser == null) {
					parser = new ParserContext(frmMain.getClass().getResourceAsStream("resources/parsedata"));
				}

				/*
				 *  Begin parsing
				 *  
				 *  if there is an error, the parser will return false
				 */
				parser.setTokens(tokens);
				if (parser.parse()) {
					System.out.println("ACCEPT");
				}
				else {
					errorDialog.setVisible(true);
					errorDialog.setLocationRelativeTo(frmMain);
					hasError = true;
				}
				
				// Switch to the Derivation tab
				frmMain.setTabIndex(2);
			}

		}

	}

	private class CommandExecuteAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;

		private CommandExecuteAction(){
			super("Execute", new ImageIcon(frmMain.getClass().getResource("resources/execute.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift X"));
			putValue(SHORT_DESCRIPTION, "Execute the command(s)");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
	
	private class ErrorDialogCloseButton extends AbstractAction {
		
		private static final long serialVersionUID = -957102144625612679L;

		@Override
		public void actionPerformed(ActionEvent e) {
			errorDialog.setVisible(false);
			hasError = false;
		}
		
	}

}
