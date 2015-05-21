package com.robotz.model;


public class TokenKeyword implements Token {
	
	private int intValue;		// in this case integer value return null
	private String charValue;	// in this case character value return null
	private String type;
	private int lineNumber;
	private int symbolTableRow;
	
	/***
	 * Constructor of TokenKeyword
	 * 
	 * This represents a token for a reserved keyword.
	 * 
	 * @param type - the type of the token (depend on the reserved keyword)
	 */
	
	public TokenKeyword(String type, String charValue, int lineNumber) {
		this.type = type;
		this.charValue = charValue;
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

	@Override
	public void setSymbolTableRow(int row) {
		
		this.symbolTableRow = row;
		
	}

	@Override
	public int getSymbolTableRow() {

		return this.symbolTableRow;
		
	}


}