package com.robotz.model;

public class TokenInteger implements Token {

	private int intValue;
	private String charValue;
	private String type;
	private int lineNumber;
	
	/***
	 * Constructor of TokenInteger
	 * 
	 * This represents a token for an integer.
	 * 
	 * @param type - the type of the token (default value is "i")
	 * @param charValue - token's char value
	 * @param intValue - token's integer value
	 */
	public TokenInteger(String type, String charValue, int intValue, int lineNumber) {
		this.type = type;
		this.charValue = charValue;
		this.intValue = intValue;
		this.lineNumber = lineNumber;
	}
	
	@Override
	public int getIntValue() {
		return intValue;
	}

	@Override
	public String getCharValue() {
		return charValue;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public int getLineNumber() {
		return lineNumber;
	}

}
