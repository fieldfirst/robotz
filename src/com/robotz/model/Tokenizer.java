package com.robotz.model;

import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class Tokenizer {
	
	private StringTokenizer lineTokenizer;
	
	private String lastError;
	private Token lastToken;
	private int lastType;
	private int currentLineNumber;
	private boolean notSkipLine;
	
	private final int ERROR = 0;
	private final int VARIABLE_TYPE = 1;
	private final int RESERVED_TYPE = 2;
	
	// Token checking regex
	private final String KEYWORD = "^(begin|halt|obstacle|add|to|move|north|south|east|west|robot|do|until|;|<|>|=)$";
	private final String VARIABLE = "^[a-zA-Z]{1,8}$";
	private final String INTEGER = "^\\d{1,8}$";
	private final String COMMENT_PART = "\\*-.*-\\*";
	
	// Error classification regex
	private final String END_WITH_SEMICOLON = "^[\\w\\d;]*;$";
	private final String TOO_LONG_VAR = "^[\\w\\d]{9,}$";
	private final String UNEND_COMMENT = "\\*-";
	
	/***
	 * Receive a line of string, then tokenizes the string
	 * 
	 * @param lineString - the input string which is separated by line
	 * @param lineNumber - set the current processing line number
	 */
	
	public void setLine(String lineString, int lineNumber) {
		// Eliminate the comment parts with an empty string
		lineString = lineString.replaceAll(COMMENT_PART, "");
		lineTokenizer = new StringTokenizer(lineString, " ");
		this.currentLineNumber = lineNumber;
		notSkipLine = true;
	}
	
	/***
	 *  Process the next token
	 */
	public void checkNextToken() {
		// Get a token then convert to the lower case
		String tk = lineTokenizer.nextToken().toLowerCase().trim();
		if (Pattern.matches(KEYWORD, tk)) {
			lastToken = new TokenKeyword(tk.substring(0, 1));		// Get the first character as a type
			lastType = RESERVED_TYPE;
		}
		else if (Pattern.matches(VARIABLE, tk)) {
			lastToken = new TokenVariable("v", tk);					// Variable type is 'v' and char value is token itself
			lastType = VARIABLE_TYPE;
		}
		else if (Pattern.matches(INTEGER, tk)) {
			lastToken = new TokenInteger("i", tk, Integer.parseInt(tk));	// Integer type is 'i', char value is token itself and int value is token's integer
			lastType = VARIABLE_TYPE;
		}
		else {
			// If can't check for the valid token then check for an error
			checkErrorToken(tk);
		}
	}
	
	private void checkErrorToken(String tk) {
		// All error checking codes go here
		if (Pattern.matches(END_WITH_SEMICOLON, tk)) {
			lastError = "In line " + currentLineNumber + ": " + tk + " (there is no separator between \"" + tk.substring(0, tk.length()-1) + "\" and " + "\";\", so they are treated as an token, which is invalid)";
			lastType = ERROR;
		}
		
		else if (Pattern.matches(TOO_LONG_VAR, tk)) {
			lastError = "In line " + currentLineNumber + ": " + tk + " : (too long)";
			lastType = ERROR;
		}
		
		else if (Pattern.matches(UNEND_COMMENT, tk)) {
			lastError = "In line " + currentLineNumber + ": " + tk + " (Everything to the right of *- is ignored as the comment is invalid because it does not end on the same line it starts on.";
			lastType = ERROR;
			notSkipLine = false;		// the right side of the un-ended comment must me ignored.
		}
		
		else {
			lastError = "In line " + currentLineNumber + ": " + tk + " is an invalid token.";
			lastType = ERROR;
		}
	}
	
	public String getLastError() {
		return lastError;
	}
	
	public Token getLastToken() {
		return lastToken;
	}
	
	public int getLastType() {
		return lastType;
	}
	
	/***
	 * Checking for an available token.
	 * @return - true if the current line is still need to be processed
	 */
	
	public boolean hasNextToken() {
		return (lineTokenizer.hasMoreTokens() && notSkipLine);
	}

}
