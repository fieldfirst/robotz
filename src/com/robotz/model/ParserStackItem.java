package com.robotz.model;

public class ParserStackItem {

	private String symbol;
	private String stateNumber;
	
	public ParserStackItem(String symbol, String stateNumber) {
		this.symbol = symbol;
		this.stateNumber = stateNumber;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getStateNumber() {
		return stateNumber;
	}
	
}
