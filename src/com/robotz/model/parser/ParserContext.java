package com.robotz.model.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.Stack;

import com.robotz.model.tokenizer.Token;
import com.robotz.model.tokenizer.TokenKeyword;

public class ParserContext {

	// All states and its own actions and goto values for each state
	private HashMap<String, ParserState> states = new HashMap<String, ParserState>();

	// current goto value
	private HashMap<String, String> gotoTable = new HashMap<String, String>();

	// Tokens are stored here
	private Queue<Token> tokens;

	// Parse stack
	private Stack<ParserStackItem> parseStack = new Stack<ParserStackItem>();

	// All grammars
	private HashMap<Integer, GrammarRule> grammars = new HashMap<Integer, GrammarRule>();

	// Result : production rules
	private ArrayList<GrammarRule> resultProductionRule = new ArrayList<GrammarRule>();
	
	// Result : for derivation
	private ArrayList<Stack<Token>> resultForDerivation = new ArrayList<Stack<Token>>();
	
	// Result : for build a parse tree
	private ArrayList<Stack<Token>> resultForParseTree = new ArrayList<Stack<Token>>();

	// Last error
	private String lastError;

	// Operation constant
	private final String SHIFT = "s";
	private final String REDUCE = "r";
	private final String ACCEPT = "acc";
	
	public ParserContext(InputStream parseTableData) {

		initStates(parseTableData);
		initGrammars();

		// Prepare a stack for parsing [terminal symbol = null, state is 0]
		parseStack.push(new ParserStackItem(null, "0", null));
	}

	private void initStates(InputStream parseTableData) {

		try {

			ArrayList<String> terminatingSymbols = new ArrayList<String>();

			BufferedReader fileReader = new BufferedReader(new InputStreamReader(parseTableData));

			final String SEPARATOR = "&";
			final String EMPTY = "";
			final int NUMBER_OF_STATE = 40;

			/*
			 * Get the first line [terminating symbols] for the Action table
			 * started from index 1 - in order to avoid "" at index 0
			 * 
			 */
			String line[] = fileReader.readLine().split(SEPARATOR);
			for (int i=1; i<line.length; i++) {
				terminatingSymbols.add(line[i]);
			}

			/*
			 * Read then create an instance for each state for the Action table [41 states, from 0 to 40]
			 * 
			 * In each line :
			 * Split the string into 2 parts by using split(regex, 2);
			 * then check the first part : if it is an EMPTY string then it must be a "&"
			 * e.g. "&&&s4" -> ["", "&&s4"]
			 * 
			 * if it doesn't then it is a action [reduce / shift]
			 * e.g. "s4&&&" -> ["s4", "&&&"]
			 * 
			 * Now the counter variable can update its value
			 * 
			 * Repeat it until EOL
			 * 
			 */
			int counter = 0;
			for (int i=0; i<=NUMBER_OF_STATE; i++) {
				line = fileReader.readLine().split(SEPARATOR, 2);
				String stateName = line[0];
				ParserState ps = new ParserState(stateName);
				counter = 0;
				while(line.length != 1) {
					line = line[1].split(SEPARATOR, 2);
					if (! line[0].equals(EMPTY)) {
						ps.setActionMap(terminatingSymbols.get(counter), line[0]);
						counter++;
					}
					else {
						counter++;
					}
					states.put(stateName, ps);
				}
			}

			ArrayList<String> nonTerminatingSymbols = new ArrayList<String>();

			/*
			 * Get the non-terminating symbols for the Goto table
			 * started from index 1 - in order to avoid "" at index 0
			 */
			line = fileReader.readLine().split(SEPARATOR);
			for (int i=1; i<line.length; i++) {
				nonTerminatingSymbols.add(line[i]);
			}

			// Read then assign the goto value for non-terminating symbols in each state [if it has]
			counter = 0;
			for (int i=0; i<=NUMBER_OF_STATE; i++) {
				line = fileReader.readLine().split(SEPARATOR, 2);
				String stateName = line[0];
				counter = 0;
				while (line.length != 1) {
					line = line[1].split(SEPARATOR, 2);
					if (! line[0].equals(EMPTY) && counter == 0) {
						states.get(stateName).setActionMap(nonTerminatingSymbols.get(counter), line[0]);
						counter++;
					}
					else if (! line[0].equals(EMPTY)) {
						states.get(stateName).setGotoMap(nonTerminatingSymbols.get(counter), line[0]);
						counter++;
					}
					else {
						counter++;
					}
				}
			}

			fileReader.close();

		} catch (FileNotFoundException e) {
			// e.printStackTrace();

		} catch (IOException e) {
			// e.printStackTrace();
		}
	}

	private void initGrammars() {
		grammars.put(0, new GrammarRule(new String[]{"P"}, "P'"));
		grammars.put(1, new GrammarRule(new String[]{"b", "i", "i", "L", "h"}, "P"));
		grammars.put(2, new GrammarRule(new String[]{"S", ";"}, "L"));
		grammars.put(3, new GrammarRule(new String[]{"L", "S", ";"}, "L"));
		grammars.put(4, new GrammarRule(new String[]{"r", "v", "T", "T"}, "S"));
		grammars.put(5, new GrammarRule(new String[]{"o", "T", "T"}, "S"));
		grammars.put(6, new GrammarRule(new String[]{"m", "v", "D", "T"}, "S"));
		grammars.put(7, new GrammarRule(new String[]{"a", "T", "t", "v"}, "S"));
		grammars.put(8, new GrammarRule(new String[]{"v", "=", "T"}, "S"));
		grammars.put(9, new GrammarRule(new String[]{"d", "L", "u", "T", ">", "T"}, "S"));
		grammars.put(10, new GrammarRule(new String[]{"v"}, "T"));
		grammars.put(11, new GrammarRule(new String[]{"i"}, "T"));
		grammars.put(12, new GrammarRule(new String[]{"n"}, "D"));
		grammars.put(13, new GrammarRule(new String[]{"s"}, "D"));
		grammars.put(14, new GrammarRule(new String[]{"e"}, "D"));
		grammars.put(15, new GrammarRule(new String[]{"w"}, "D"));
	}

	public void setTokens(Queue<Token> tokens) {
		this.tokens = tokens;

		// Add the end-of-file
		this.tokens.add(new TokenKeyword("$", "$", 0));
	}

	public boolean parse() {
		
		// Store current state
		ParserState currentState = states.get("0");		// Peek the input, then select the initial state
		
		gotoTable.put("P", "1");

		while (! tokens.isEmpty()) {
			
			if (currentState.getActionMap(tokens.peek().getType()) != null) {
				
				// Shift operation
				if (currentState.getActionMap(tokens.peek().getType()).get("operation").equals(SHIFT)) {	
					
					parseStack.push(new ParserStackItem(tokens.peek().getType(), currentState.getActionMap(tokens.peek().getType()).get("number"), tokens.poll()));
					
				}
				
				// Reduce operation
				else if (currentState.getActionMap(tokens.peek().getType()).get("operation").equals(REDUCE)) {
					
					int ruleNumber = Integer.parseInt(currentState.getActionMap(tokens.peek().getType()).get("number"));					
					int numberOfTokens = grammars.get(ruleNumber).getExpressionSize();
					
					Stack<String> expression = new Stack<String>();
					Stack<Token> forDerivationOutput = new Stack<Token>();
					Stack<Token> forParseTreeOutput = new Stack<Token>();
					for (int i=0; i<numberOfTokens; i++) {
						forDerivationOutput.push(parseStack.peek().getToken());
						forParseTreeOutput.push(parseStack.peek().getToken());
						expression.push(parseStack.pop().getSymbol());
					}
					
					// Update the goto table
					if (! parseStack.isEmpty())	{
						
						ParserState st = states.get(parseStack.peek().getStateNumber());
					
						if (st.hasGotoMap()) {
							for (String nonTerminalSymbol : st.getGotoMap().keySet()) {
								gotoTable.put(nonTerminalSymbol, st.getGotoMap().get(nonTerminalSymbol));
							}
						}
					}
					
					resultForParseTree.add(forParseTreeOutput);
					resultForDerivation.add(forDerivationOutput);
					if (grammars.get(ruleNumber).evaluate(expression)) {
						parseStack.push(new ParserStackItem(grammars.get(ruleNumber).getLeftSymbol(), gotoTable.get(grammars.get(ruleNumber).getLeftSymbol()), tokens.peek()));
						resultProductionRule.add(grammars.get(ruleNumber));
					}
					else {
						// Reduce failed
						this.lastError = "";
						break;
					}
				}
				// Accept state
				else if (currentState.getActionMap(tokens.peek().getType()).get("operation").equals(ACCEPT)) {
					return true;
				}
			}
			else {
				// if getActionMap method of the current state return null, spawn an error [No symbol map found]
				this.lastError = "Syntax's error in line " + tokens.peek().getLineNumber() + " : " + tokens.peek().getCharValue() + " is <" + ParserUtility.getTypeToStringMap(tokens.peek().getType()) + ">, expected " + currentState.getAllPossibleTerminalSymbols();
				break;
			}

			// Set a new current state
			currentState = states.get(parseStack.peek().getStateNumber());

		}

		return false;
	}

	public String getLastError() {
		return this.lastError;
	}
	
	public ArrayList<GrammarRule> getResultProductionRule() {
		return this.resultProductionRule;
	}
	
	public ArrayList<Stack<Token>> getResultForDerivation() {
		return this.resultForDerivation;
	}
	
	public ArrayList<Stack<Token>> getResultForParseTree() {
		return this.resultForParseTree;
	}
	
	public void clearLastResult() {
		resultProductionRule.clear();
		resultForDerivation.clear();
		resultForParseTree.clear();
		parseStack.clear();
	}
}
