package com.robotz.model;

public class TokenInteger implements Token {

	private int intValue;
	private String charValue;
	private String type;
	
	public TokenInteger(String type, String charValue, int intValue) {
		this.type = type;
		this.charValue = charValue;
		this.intValue = intValue;
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
