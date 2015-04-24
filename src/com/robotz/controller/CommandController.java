package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import com.robotz.model.ParserContext;
import com.robotz.model.Token;
import com.robotz.model.Tokenizer;
import com.robotz.model.grammar.GrammarRule;

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
				 * 	Clear the last parse result
				 *  Begin parsing
				 *  
				 *  if there is an error, the parser will return false
				 */
				frmMain.clearDerivationItem();
				parser.setTokens(tokens);
				parser.clearLastResult();

				if (parser.parse()) {
					ArrayList<GrammarRule> resultRule = parser.getResultProductionRule();
					ArrayList<Stack<Token>> resultDerivation = parser.getResultForDerivation();

					int size = resultRule.size() - 1;
					String derivationIncremental = "";

					for (int i=size; i>=0; i--) {
						String[] splitRule = resultRule.get(i).getProductionRuleAsString().split(" ", 3);
						String leftSymbol = splitRule[0];
						String[] rightSymbol = splitRule[2].split(" ");
						for (int k=0; k<rightSymbol.length; k++) {
							if (resultDerivation.get(i).peek().getType().equals("i")) {
								rightSymbol[k] = rightSymbol[k].replace("int", resultDerivation.get(i).pop().getCharValue());
							}
							else if (resultDerivation.get(i).peek().getType().equals("v")) {
								rightSymbol[k] = rightSymbol[k].replace("var", resultDerivation.get(i).pop().getCharValue());
							}
							else {
								resultDerivation.get(i).pop();
							}
						}
						String derivation = "";
						for (int k=0; k<rightSymbol.length; k++) {
							derivation += rightSymbol[k].trim() + " ";
						}
						if (derivationIncremental.contains(leftSymbol)) {
							String[] d = derivationIncremental.split(" ");
							for (int g=d.length-1; g>=0; g--) {
								if (d[g].equals(leftSymbol)) {
									d[g] = derivation;
									break;
								}
							}
							derivationIncremental = "";
							for (int a=0; a<d.length; a++) {
								derivationIncremental += d[a] + " "; 
							}
						}
						else {
							derivationIncremental = derivation; // begin
						}
						frmMain.addDerivationItem(resultRule.get(i).getProductionRuleAsString(), derivationIncremental);
					}
				}

				else {
					errorDialog.clearError();
					errorDialog.appendError(parser.getLastError());
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
