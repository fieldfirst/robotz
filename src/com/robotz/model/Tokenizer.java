package com.robotz.model;

import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class Tokenizer {
	
	private StringTokenizer lineTokenizer;
	
	private String lastError;
	private Token lastToken;
	private int lastType;
	private int currentLineNumber;
	
	private final int ERROR = 0;
	private final int VARIABLE_TYPE = 1;
	private final int RESERVED_TYPE = 2;
	
	private final String KEYWORD = "^(begin|halt|obstacle|add|to|move|north|south|east|west|robot|do|until|;|<|>|=)$";
	private final String VARIABLE = "^[a-zA-Z]{1,8}$";
	private final String INTEGER = "^\\d{1,8}$";
	private final String COMMENT_PART = "\\*-.*-\\*";
	
	public void setLine(String recvToken, int lineNumber) {
		// Eliminate the comment parts with an empty string
		recvToken = recvToken.replaceAll(COMMENT_PART, "");
		lineTokenizer = new StringTokenizer(recvToken, " ");
		this.currentLineNumber = lineNumber;
	}
	
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
	
	public boolean hasNextToken() {
		return (lineTokenizer.hasMoreTokens());
	}

}
