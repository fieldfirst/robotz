package com.robotz.model;

public class TokenVariable implements Token {

	private int intValue;		// in this case integer value return null
	private String charValue;
	private String type;
	
	public TokenVariable(String type, String charValue) {
		this.type = type;
		this.charValue = charValue;
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
