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

import com.robotz.model.ExecuteEngine;
import com.robotz.model.ParserContext;
import com.robotz.model.Token;
import com.robotz.model.TokenInteger;
import com.robotz.model.TokenKeyword;
import com.robotz.model.Tokenizer;
import com.robotz.model.grammar.GrammarRule;
import com.robotz.model.parsetree.Node;
import com.robotz.model.parsetree.NodeAdd;
import com.robotz.model.parsetree.NodeAssign;
import com.robotz.model.parsetree.NodeBegin;
import com.robotz.model.parsetree.NodeDo;
import com.robotz.model.parsetree.NodeMove;
import com.robotz.model.parsetree.NodeObstacle;
import com.robotz.model.parsetree.NodeRobot;
import com.robotz.model.parsetree.NodeSequence;
import com.robotz.model.parsetree.STStackItem;

public class CommandController extends Controller {

	private CommandTokenizeAction commandTokenizeAction;
	private CommandCompileAction commandCompileAction;
	private CommandExecuteAction commandExecuteAction;

	private ErrorDialogCloseButton errorDialogCloseButton;

	// All tokens are stored here
	private Queue<Token> tokens = new LinkedList<Token>();

	// if there is an error in a previous operation all later operation(s) will stop
	private boolean hasError = false;
	
	// Store the parse tree's root node
	private Node root;

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
						tk.setSymbolTableRow(frmMain.getCurrentSymbolTableRow());
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
		
		// Initialize the STstack [ contains tokens and nodes ]
		private Stack<STStackItem> STstack = new Stack<STStackItem>();

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
					ArrayList<Stack<Token>> resultToken = parser.getResultForDerivation();
					ArrayList<Stack<Token>> resultTokenAnother = parser.getResultForParseTree();

					// Output the derivation result
					printDerivation(resultRule, resultToken);
					
					// Build a parse tree
					buildParseTree(resultRule, resultTokenAnother);
					
				}

				else {
					errorDialog.clearError();
					errorDialog.appendError(parser.getLastError());
					errorDialog.setVisible(true);
					errorDialog.setLocationRelativeTo(frmMain);
					hasError = true;
				}

				// Switch to the Derivation tab
				frmMain.autoResizeColumnWidth();
				frmMain.setTabIndex(2);
			}

		}
		
		private void printDerivation(ArrayList<GrammarRule> resultRule, ArrayList<Stack<Token>> resultToken) {
			
			int size = resultRule.size() - 1;
			
			// Store a full length of string
			String derivationIncremental = "";

			// Get each rule, started from the last rule
			for (int i=size; i>=0; i--) {

				// Split, get the left symbol and right symbol as string array
				String[] splitRule = resultRule.get(i).getProductionRuleAsString().split(" ", 3);
				String leftSymbol = splitRule[0];
				String[] rightSymbol = splitRule[2].split(" ");
				
				// Replace the right symbol with its value
				for (int k=0; k<rightSymbol.length; k++) {
					if (resultToken.get(i).peek().getType().equals("i")) {
						rightSymbol[k] = rightSymbol[k].replace("int", resultToken.get(i).pop().getCharValue());
					}
					else if (resultToken.get(i).peek().getType().equals("v")) {
						rightSymbol[k] = rightSymbol[k].replace("var", resultToken.get(i).pop().getCharValue());
					}
					else {
						resultToken.get(i).pop();
					}
				}
				
				// Join all right symbols string back together
				String derivation = "";
				for (int k=0; k<rightSymbol.length; k++) {
					if (k < rightSymbol.length-1)
						derivation += rightSymbol[k].trim() + " ";
					else
						derivation += rightSymbol[k].trim();
				}
				
				// Replace the left symbol with the its right symbols [started from the right]
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
				
				// Add the item to the derivation table
				frmMain.addDerivationItem(resultRule.get(i).getProductionRuleAsString(), derivationIncremental);
			}
			
		}
		
		private void buildParseTree(ArrayList<GrammarRule> resultRule, ArrayList<Stack<Token>> resultToken) {
			
			/*
			for (int i=0; i<resultRule.size(); i++) {
				
				String tokens = "";
				
				while (! resultToken.get(i).isEmpty()) {
					tokens += resultToken.get(i).pop().getCharValue() + " ";
				}
				
				System.out.println(resultRule.get(i).getProductionRuleAsString() + "\t\t\t\t" + tokens);
				
			}
			*/
			
			// Starting to build a parse tree
			
			for (int i=0; i<resultRule.size(); i++) {
				
				String leftSymbol = resultRule.get(i).getLeftSymbol();
							
				String[] rightSymbol = resultRule.get(i).getProductionRuleAsString().split(" ", 3)[2].split(" ");
							
				if (leftSymbol.equals("T") || leftSymbol.equals("D")) {
						
					Token tk = resultToken.get(i).pop();
							
					STstack.push(new STStackItem(leftSymbol, tk));

				}
				
				else {
					
					if (rightSymbol[0].equals("robot")) {
						
						resultToken.get(i).pop();
						
						createRobotNode(leftSymbol, resultToken.get(i).pop().getCharValue(), resultRule.get(i).getProductionRuleAsString());
												
					}
						
					else if (rightSymbol[0].equals("obstacle")) {
						
						createObstacleNode(leftSymbol, resultRule.get(i).getProductionRuleAsString());
												
					}

					else if (rightSymbol[0].equals("move")) {
						
						resultToken.get(i).pop();
						
						createMoveNode(leftSymbol, resultToken.get(i).pop().getCharValue(), resultRule.get(i).getProductionRuleAsString());
												
					}
						
					else if (rightSymbol[0].equals("add")) {
						
						resultToken.get(i).pop();
						
						resultToken.get(i).pop();
						
						resultToken.get(i).pop();
						
						createAddNode(leftSymbol, resultToken.get(i).pop().getCharValue(), resultRule.get(i).getProductionRuleAsString());
						
					}
						
					else if (rightSymbol[0].equals("var")) {
						
						createAssignNode(leftSymbol, resultToken.get(i).pop().getCharValue(), resultRule.get(i).getProductionRuleAsString());
												
					}
						
					else if (rightSymbol[0].equals("L")) {
						
						createSequenceNode(leftSymbol, resultRule.get(i).getProductionRuleAsString());
												
					}
					
					else if (rightSymbol[0].equals("do")) {
						
						createNodeDo(leftSymbol, resultRule.get(i).getProductionRuleAsString());
											
					}
					
					else if (rightSymbol[0].equals("begin")) {
						
						createBeginNode(resultToken, i, resultRule.get(i).getProductionRuleAsString());

					}
										
				}

			}
			
		}

		private void createBeginNode(ArrayList<Stack<Token>> resultToken, int i, String productionRule) {
			
			NodeBegin node = new NodeBegin();
			
			node.setProductionRuleAsString(productionRule);
			
			resultToken.get(i).pop();
			
			TokenInteger mapWidth = (TokenInteger) resultToken.get(i).pop();
			
			TokenInteger mapHeight = (TokenInteger) resultToken.get(i).pop();
			
			node.setMapWidth(mapWidth.getIntValue());
			
			node.setMapHeight(mapHeight.getIntValue());
			
			node.setNode((Node) STstack.pop().getItem());
			
			root = node;
			
		}

		private void createSequenceNode(String leftSymbol, String productionRule) {
			
			NodeSequence node = new NodeSequence();
			
			node.setProductionRuleAsString(productionRule);
			
			node.setBNode((Node) STstack.pop().getItem());
			
			node.setANode((Node) STstack.pop().getItem());
			
			STstack.push(new STStackItem(leftSymbol, node));
			
		}

		private void createNodeDo(String leftSymbol, String productionRule) {
			
			NodeDo node = new NodeDo();
			
			node.setProductionRuleAsString(productionRule);
			
			node.setB((Token) STstack.pop().getItem());
			
			node.setA((Token) STstack.pop().getItem());
			
			node.setNode((Node) STstack.pop().getItem());
			
			STstack.push(new STStackItem(leftSymbol, node));
			
		}

		private void createAssignNode(String leftSymbol, String variableName, String productionRule) {
			
			NodeAssign node = new NodeAssign();
			
			node.setProductionRuleAsString(productionRule);
			
			node.setVariableName(variableName);
			
			Token value = (Token) STstack.pop().getItem();
			
			node.setValue(value);
			
			STstack.push(new STStackItem(leftSymbol, node));
			
		}

		private void createAddNode(String leftSymbol, String variableName, String productionRule) {
			
			NodeAdd node = new NodeAdd();
			
			node.setProductionRuleAsString(productionRule);
			
			node.setVariableName(variableName);
			
			Token value = (Token) STstack.pop().getItem();
			
			node.setValue(value);
			
			STstack.push(new STStackItem(leftSymbol, node));
			
		}

		private void createMoveNode(String leftSymbol, String variableName, String productionRule) {
			
			NodeMove node = new NodeMove();
			
			node.setProductionRuleAsString(productionRule);
			
			node.setVariableName(variableName);
			
			Token distance = (Token) STstack.pop().getItem();
			
			node.setDistance(distance);
			
			TokenKeyword direction = (TokenKeyword) STstack.pop().getItem();
			
			node.setDirection(direction);
			
			STstack.push(new STStackItem(leftSymbol, node));
			
		}

		private void createObstacleNode(String leftSymbol, String productionRule) {
			
			NodeObstacle node = new NodeObstacle();
			
			node.setProductionRuleAsString(productionRule);
			
			Token yPosition = (Token) STstack.pop().getItem();
			
			node.setyPosition(yPosition);
			
			Token xPosition = (Token) STstack.pop().getItem();
			
			node.setxPosition(xPosition);
			
			STstack.push(new STStackItem(leftSymbol, node));
			
		}

		private void createRobotNode(String leftSymbol, String variableName, String productionRule) {
			
			NodeRobot node = new NodeRobot();
			
			node.setProductionRuleAsString(productionRule);
			
			node.setVariableName(variableName);
			
			Token yPosition = (Token) STstack.pop().getItem();
			
			node.setyPosition(yPosition);
			
			Token xPosition = (Token) STstack.pop().getItem();
			
			node.setxPosition(xPosition);
			
			STstack.push(new STStackItem(leftSymbol, node));
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
			
			// Before parsing process, we must scan and tokenize first
			commandTokenizeAction.actionPerformed(arg0);
			
			// Before the animation process, we must build a parse tree first
			commandCompileAction.actionPerformed(arg0);
			
			if (! hasError) {
				
				animationDialog.setVisible(true);
				animationDialog.setLocationRelativeTo(frmMain);
				animationDialog.clear();
			
				ExecuteEngine en = new ExecuteEngine(root, animationDialog, frmMain);
				
				en.startAnimation();
				
				// Switch to the Symbol table tab
				frmMain.setTabIndex(1);
						
			}

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
