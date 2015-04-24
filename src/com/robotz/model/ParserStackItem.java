package com.robotz.model;

public class ParserStackItem {

	private String symbol;
	private String stateNumber;
	private Token token;
	
	public ParserStackItem(String symbol, String stateNumber, Token token) {
		this.symbol = symbol;
		this.stateNumber = stateNumber;
		this.token = token;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getStateNumber() {
		return stateNumber;
	}
	
	public Token getToken() {
		return token;
	}
	
}
