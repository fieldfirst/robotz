package com.robotz.model;

public class TokenKeyword implements Token {
	
	private int intValue;		// in this case integer value return null
	private String charValue;	// in this case character value return null
	private String type;
	
	public TokenKeyword(String type) {
		this.type = type;
		this.charValue = null;
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

}